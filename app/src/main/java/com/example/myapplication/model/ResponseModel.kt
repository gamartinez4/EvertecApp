package com.example.myapplication.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResponseModel (
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val firstName:String,
    val lastName:String,
    val image:String,
    val description:String,
    val rating:String
)