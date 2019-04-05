package com.example.iutsummer.ui.user.news.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.iutsummer.R

class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    var image:ImageView = view.findViewById(R.id.imageplace)
    var date:TextView = view.findViewById(R.id.dateText)
    var month:TextView = view.findViewById(R.id.monthText)
    var title:TextView = view.findViewById(R.id.titletext)
    var body:TextView = view.findViewById(R.id.bodytext)
}