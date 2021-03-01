package com.example.androiddevchallenge.core.model

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tm_puppys")
data class PuppyEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id_puppy:Int,
    val puppy_name:String = "",
    val puppy_detail:String= "",
    @Nullable
    val puppy_tips:String?= "",
    @Nullable
    val puppy_healt_issue:String?= "",
    val puppy_life_span:String= "",
    @Nullable
    val puppy_behavior:String?= "",
    val puppy_size:String= "",
    val puppy_weight:String= "",
    val puppy_height:String= "",
    val puppy_image:String= ""
)
