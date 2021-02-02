package com.example.retrofit_1221

import com.example.retrofit_1221.POJO.MarsTerrain
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MarsAPI {

   //lLa vieja confiable
    @GET ("realestate")
    fun fetchMarsTerrainEnqueue(): Call<List<MarsTerrain>>

    //La forma recomendable nueva
    @GET("realestate")
    suspend fun fetchMarsTerrainCoroutines(): Response<List<MarsTerrain>>

}