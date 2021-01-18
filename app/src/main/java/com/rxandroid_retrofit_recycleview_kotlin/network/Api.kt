package com.rxandroid_retrofit_recycleview_kotlin.network


import com.rxandroid_retrofit_recycleview_kotlin.data.News
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "82e76a4e6d4540909a9bb6eaca329868"
    }

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getAllData(@Query("country") country: String, @Query("page") page: Int): Observable<News>
    // here we assume to get all data from the server in form of news ,and store this data in observable
}