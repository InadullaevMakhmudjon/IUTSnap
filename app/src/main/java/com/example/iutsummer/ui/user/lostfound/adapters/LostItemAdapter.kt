package com.example.iutsummer.ui.user.lostfound.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.iutsummer.R
import com.example.iutsummer.data.db.entity.LItem
import com.example.iutsummer.utils.showMessage
import kotlinx.android.synthetic.main.lost_item.view.*

class LostItemAdapter(val context:Context): RecyclerView.Adapter<LostItemViewHolder>() {

    private lateinit var allItems:List<LItem>

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LostItemViewHolder {
        return LostItemViewHolder(LayoutInflater.from(context).inflate(R.layout.lost_item,p0,false))
    }

    override fun getItemCount(): Int = allItems.size

    override fun onBindViewHolder(p0: LostItemViewHolder, p1: Int) {
        p0.v.category.text = allItems[p1].category
        p0.v.description.text = allItems[p1].description
        p0.v.date.text = allItems[p1].date
        Glide.with(context).load(allItems[p1].photo).into(p0.v.image)
        p0.v.details.setOnClickListener {
            it.showMessage(allItems[p1].category)
        }
    }

    fun setData(data:List<LItem>){
        allItems = data
        notifyDataSetChanged()
    }
}