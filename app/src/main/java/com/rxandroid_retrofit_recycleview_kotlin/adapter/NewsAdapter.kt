package com.rxandroid_retrofit_recycleview_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rxandroid_retrofit_recycleview_kotlin.R
import com.rxandroid_retrofit_recycleview_kotlin.data.Article
import kotlinx.android.synthetic.main.item_row.view.*


class NewsAdapter(private var contect: Context, private var articleList: ArrayList<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articleList[position]
        holder.title.text = article.title
        holder.description.text = article.description

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        Glide.with(contect)
            .load(article.urlToImage)
            .apply(options)
            .into(holder.image)

    }

    fun setData(articleList: ArrayList<Article>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.title
        val description = itemView.description
        val image = itemView.image
    }

}