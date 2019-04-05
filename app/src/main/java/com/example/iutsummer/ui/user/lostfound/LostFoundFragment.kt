package com.example.iutsummer.ui.user.lostfound

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iutsummer.App
import com.example.iutsummer.R
import com.example.iutsummer.databinding.LayoutLostFoundBinding
import com.example.iutsummer.ui.user.lostfound.adapters.LostItemAdapter
import kotlinx.android.synthetic.main.layout_lost_found.*

class LostFoundFragment: Fragment() {

    lateinit var binding:LayoutLostFoundBinding
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_lost_found,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this,
            LostFoundVMF(activity!!.application as App,this)
        ).get(LostFoundVM::class.java)
        binding.viewm = viewModel
        binding.lifecycleOwner = this
        binding.viewm!!.getAllItems()
        var adapter = LostItemAdapter(this.context!!)
        binding.viewm!!.allItems.observe(this, Observer{
            if(it!=null)
                adapter.setData(it)

            lostItems.adapter = adapter
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            binding.viewm!!.setImage()
        }
    }
}