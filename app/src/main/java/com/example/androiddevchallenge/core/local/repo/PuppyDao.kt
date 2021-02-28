package com.example.androiddevchallenge.core.local.repo

import androidx.room.Dao
import androidx.room.Query
import com.example.androiddevchallenge.core.model.PuppyEntity

@Dao
interface PuppyDao {

    @Query("SELECT * FROM tm_puppys where id_puppy == :id")
    suspend fun getPuppys(id:Int):PuppyEntity

    @Query("SELECT * FROM tm_puppys")
    suspend fun getPuppys():List<PuppyEntity>

}