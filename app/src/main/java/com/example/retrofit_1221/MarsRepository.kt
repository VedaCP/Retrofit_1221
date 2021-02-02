package com.example.retrofit_1221

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_1221.POJO.MarsTerrain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarsRepository {
    private val services = RetrofitClient.retrofitInstance()
    val liveDataMarsTerrain = MutableLiveData<List<MarsTerrain>>()

    //Vieja confiable enqueue desde retrofit
    fun fetchMarsTerrainEnqueue(): LiveData<List<MarsTerrain>> {
        services.fetchMarsTerrainEnqueue().enqueue(object : Callback<List<MarsTerrain>> {
            override fun onResponse(
                call: Call<List<MarsTerrain>>,
                response: Response<List<MarsTerrain>>
            ) {
                when (response.code()) {
                    in 200..299 -> liveDataMarsTerrain.value = response.body()
                    in 300..399 -> Log.d("ERROR", response.errorBody().toString())
                    else -> Log.d("ERROR", "error server : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<MarsTerrain>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

        })
        return liveDataMarsTerrain
    }

    suspend fun getTerrainWithCoroutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        val response = RetrofitClient.retrofitInstance().fetchMarsTerrainCoroutines()
        when(response.isSuccessful) {
            true -> response.body()?.let {li}
        }
    }


}