package com.example.myapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.ResponseModel

@Database(
    entities = [ResponseModel::class],
    version = 1
    )
abstract class DataBase : RoomDatabase(){

    abstract fun responseModelDAOCreater():ResponseModelDAO

}