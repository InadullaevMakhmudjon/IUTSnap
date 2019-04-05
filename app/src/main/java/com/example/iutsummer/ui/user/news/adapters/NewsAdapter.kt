package com.example.iutsummer.ui.user.news.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.iutsummer.R
import com.example.iutsummer.data.db.entity.News
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(val news:List<News>,val context:Context?): RecyclerView.Adapter<NewsViewHolder>() {

    private var data:List<News> = news

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item,p0,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: NewsViewHolder, p1: Int) {
        Glide.with(context).load(data[p1].image).into(p0.image)
        p0.title.text = data[p1].titleText
        p0.body.text = data[p1].bodyText
        p0.date.text = data[p1].date
        p0.month.text = data[p1].month
        p0.view.setOnClickListener{
            Toast.makeText(context,"Hey",Toast.LENGTH_SHORT).show()
        }
     }

    fun setData(d:List<News>){
        data = d
        notifyDataSetChanged()
    }
}