package com.example.myapplication

import com.example.myapplication.proxi.RetrofitController
import com.example.myapplication.proxi.RetrofitStrings
import org.koin.dsl.module

val modKoin = module{
    single { RetrofitStrings() }
    single { RetrofitController()}
    single {DialogPersonalized()}
}