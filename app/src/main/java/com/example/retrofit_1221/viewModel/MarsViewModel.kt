package com.example.retrofit_1221.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.retrofit_1221.model.POJO.MarsTerrain
import com.example.retrofit_1221.model.local.MarsDatabase
import com.example.retrofit_1221.model.remote.MarsRepository
import kotlinx.coroutines.launch


class MarsViewModel(application: Application) : AndroidViewModel(application) {

   private val repository: MarsRepository
   val marsTerrainLiveDataFromDB: LiveData<List<MarsTerrain>>

   init {
       val dao = MarsDatabase.getDataBase(application).getMarsDao()
       repository = MarsRepository(dao)
        viewModelScope.launch {
            repository.getTerrainWithCoroutines()
        }
       marsTerrainLiveDataFromDB = repository.liveDataMarsTerrainDB
    }

    //Este sería para actualizar desde internet los datos
    fun getFetchTerrainsWhithCoroutines() = viewModelScope.launch{
        repository.getTerrainWithCoroutines()
    }

    fun getTerrainById(id: String) : LiveData<MarsTerrain> {
        return repository.getTerrainById(id)
    }

}