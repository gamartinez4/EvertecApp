package com.example.myapplication.room
import androidx.room.*
import com.example.myapplication.model.ResponseModel

@Dao
interface ResponseModelDAO {
    @Query("SELECT * FROM ResponseModel")
    suspend fun getAll():List<ResponseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToList(responseModelList: ResponseModel)

    @Delete
    suspend fun delete(responseModel: ResponseModel)

}