package com.example.retrofit_1221.POJO

import com.google.gson.annotations.SerializedName

//(POJO)
data class MarsTerrain (
                 @SerializedName("id")
                 val id: String,
                 @SerializedName("price")
                 val price: Long,
                 @SerializedName("type")
                 val type: String,
                 @SerializedName("src_img")
                 val srcImg: String)


