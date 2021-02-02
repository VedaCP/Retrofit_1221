package com.example.retrofit_1221

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_1221.POJO.MarsTerrain
import kotlinx.coroutines.launch

class MarsViewModel : ViewModel() {

    private val repository: MarsRepository
    init {
        repository = MarsRepository()
        viewModelScope.launch {
            repository.getTerrainWithCoroutines()
        }
    }

    //Voy a observar las vistas y realizar la solicitud de datos
    fun getFetchTerrains() : LiveData<List<MarsTerrain>> {
        return repository.fetchMarsTerrainEnqueue()
    }
    fun getFetchTerrainsWhithCoroutines() : LiveData<List<MarsTerrain>> {
        return repository.liveDataMarsTerrain


    }
}