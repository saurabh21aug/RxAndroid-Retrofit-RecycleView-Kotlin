package com.rxandroid_retrofit_recycleview_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rxandroid_retrofit_recycleview_kotlin.adapter.NewsAdapter
import com.rxandroid_retrofit_recycleview_kotlin.data.Article
import com.rxandroid_retrofit_recycleview_kotlin.data.News
import com.rxandroid_retrofit_recycleview_kotlin.network.Retrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

//    companion object {
//        private val TAG: String? = MainActivity::class.simpleName
//    }

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsAdapter =
            NewsAdapter(
                this,
                ArrayList()
            )

        // The Kotlin standard library contains several functions whose sole purpose is to execute a block of code within the context of an object.
        // When you call such a function on an object with a lambda expression provided, it forms a temporary scope.
        // In this scope, you can access the object without its name. Such functions are called scope functions.
        // There are five of them: let, run, with, apply, and also.
        // scope function
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
            visibility = View.GONE
        }


        val compositeDisposable = CompositeDisposable()
        compositeDisposable
            .add(
                getObservable().subscribeOn(io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onNext ->
                        getObserver(onNext.articles)
                    }, { onError -> onFailure(onError) })
            )
    }

    private fun getObservable(): Observable<News> {

        val response = Retrofit.api.getAllData("in", 1)
        return response
    }

    private fun getObserver(articleList: ArrayList<Article>) {
        if (articleList.size >= 0) {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            newsAdapter.setData(articleList)
        }
    }

    private fun onFailure(t: Throwable) {
        println(t)
        Toast.makeText(
            applicationContext, "$t", Toast.LENGTH_SHORT
        ).show()
    }


}