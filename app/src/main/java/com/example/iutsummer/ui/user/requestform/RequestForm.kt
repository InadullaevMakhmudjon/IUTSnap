package com.example.iutsummer.ui.user.requestform

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.iutsummer.App
import com.example.iutsummer.R
import com.example.iutsummer.databinding.FragmentRequestFormBinding

class RequestForm : Fragment() {

    lateinit var binding:FragmentRequestFormBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_request_form,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.
            of(this,ViewModelFactory(this.activity!!.application as App,binding))
            .get(RequestViewModel::class.java)

        viewModel.success.observe(this, Observer {
            Navigation.findNavController(this.activity!!,R.id.allcaontainer).navigate(R.id.newsPage)
        })

        binding.rviewModel = viewModel
        binding.lifecycleOwner = this

    }
}
