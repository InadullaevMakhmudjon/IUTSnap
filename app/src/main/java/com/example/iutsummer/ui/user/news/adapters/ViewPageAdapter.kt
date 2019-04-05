package com.example.iutsummer.ui.user.news.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.iutsummer.R
import com.example.iutsummer.data.db.entity.News


class ViewPageAdapter(cont:Context?,data:List<News>?): PagerAdapter() {

    private val context = cont
    private val news = data

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0==p1
     }

    override fun getCount(): Int {
        if(news!=null)
            return news.size

        return 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.image_layout, null)
        val imageView = view.findViewById<ImageView>(R.id.newsImageOriginal)
        val titleText = view.findViewById<TextView>(R.id.titletext)
        val contentText = view.findViewById<TextView>(R.id.contenttext)
        if(news!=null) {
            Glide.with(context).load(news[position].image).into(imageView)
            titleText.text = news[position].titleText
            contentText.text = news[position].bodyText
        }
        (container as ViewPager).addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}