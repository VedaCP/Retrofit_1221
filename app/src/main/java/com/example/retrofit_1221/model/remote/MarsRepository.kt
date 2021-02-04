package com.example.retrofit_1221.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_1221.model.POJO.MarsTerrain
import com.example.retrofit_1221.model.local.MarsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarsRepository(private val dao: MarsDao) {

    private val services = RetrofitClient.retrofitInstance()
    val liveDataMarsTerrainDB = dao.getAllTerrainsDB()


    //Función que utiliza las coroutinas para la conexión del servicio
    suspend fun getTerrainWithCoroutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitClient.retrofitInstance().fetchMarsTerrainCoroutines()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    //Acá se está insertando en la base de datos
                    dao.insertAllTerrains(it)
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }
        }

        catch(t: Throwable) {
            Log.e("ERROR COROUTINA", t.message.toString())
        }
    }

    fun getTerrainById(id: String) : LiveData<MarsTerrain> {
        return dao.getTerrainById(id)
    }
}