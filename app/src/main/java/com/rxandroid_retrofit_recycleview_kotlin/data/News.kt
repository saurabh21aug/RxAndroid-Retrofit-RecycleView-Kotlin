package com.rxandroid_retrofit_recycleview_kotlin.data

data class News(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)