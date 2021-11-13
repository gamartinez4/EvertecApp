package com.example.myapplication.proxi

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class RetrofitController{

    fun executeAPI(
        url:String,
        body: RequestBody,
        goodFunction: (response:Response<String>) -> Any,
        badFunction: () -> Any
    ): @NonNull Disposable? {
       return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(ApiRetrofit::class.java).getAllPost("",body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        goodFunction(it)
                    },
                    {
                        badFunction()
                    }
                )
    }

}