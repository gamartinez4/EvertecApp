package com.example.myapplication.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResponseModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var names:String = "",
    var surnames:String =  "",
    var creditNumber:String = "",
    var price:String = "",
    var buyDate:String = "",
    var state:String = ""
)