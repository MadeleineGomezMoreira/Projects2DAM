package com.example.recyclerdetail.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerdetail.data.model.ThingEntity

@Dao
interface ThingDao {

    @Query("SELECT * FROM Things")
    fun getThings(): List<ThingEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: ThingEntity)
}