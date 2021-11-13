package com.example.myapplication.room
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.ResponseModel

@Dao
interface ResponseModelDAO {
    @Query("SELECT * FROM ResponseModel")
    suspend fun getAll():List<ResponseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(responseModelList: ArrayList<ResponseModel>)

}