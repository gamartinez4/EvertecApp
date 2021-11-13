package com.example.myapplication.proxi

import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiRetrofit {

    @POST
    fun getAllPost(@Url url:String,@Body body: RequestBody): Observable<Response<String>>

}