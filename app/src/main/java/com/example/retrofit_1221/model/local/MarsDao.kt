package com.example.retrofit_1221.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.retrofit_1221.model.POJO.MarsTerrain

@Dao
interface MarsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTerrains(list: List<MarsTerrain>)

    @Query("SELECT * FROM mars_terrain_table")
    fun getAllTerrainsDB(): LiveData<List<MarsTerrain>>

    @Query("SELECT * FROM mars_terrain_table WHERE id = :id")
    fun getTerrainById(id: String) : LiveData<MarsTerrain>


}