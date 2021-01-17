package com.rxandroid_retrofit_recycleview_kotlin.network


import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//http://newsapi.org/v2/everything?q=bitcoin&from=2020-12-16&sortBy=publishedAt&apiKey=82e76a4e6d4540909a9bb6eaca329868
class Retrofit {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val api: Api = retrofit.create(
            Api::class.java
        )
    }
}