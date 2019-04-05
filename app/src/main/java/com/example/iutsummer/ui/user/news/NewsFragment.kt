package com.example.iutsummer.ui.user.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iutsummer.R
import com.example.iutsummer.databinding.FragmentNewsPageBinding
import com.example.iutsummer.ui.user.news.adapters.NewsAdapter
import com.example.iutsummer.ui.user.news.adapters.ViewPageAdapter


class NewsFragment : Fragment() {

    lateinit var binding:FragmentNewsPageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding =DataBindingUtil.inflate(inflater, R.layout.fragment_news_page,container,false)
            return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val newsViewModel = ViewModelProviders
            .of(this,ViewModelProvider.AndroidViewModelFactory(activity!!.application))
            .get(NewsViewModel::class.java)
        newsViewModel.initializeNews()
        var adapter: ViewPageAdapter
        var itemadapter:NewsAdapter
        newsViewModel.allNews.observe(this.activity!!, Observer {
            if(it!=null){
                adapter = ViewPageAdapter(
                    this.context,
                    it
                )
                itemadapter = NewsAdapter(it,this.context)
                binding.newsImages.adapter = adapter
                binding.latestnewstholder.adapter = itemadapter
            }
        })

        binding.newsImages.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

             }

            override fun onPageSelected(p0: Int) {

            }

        })

    }



}
