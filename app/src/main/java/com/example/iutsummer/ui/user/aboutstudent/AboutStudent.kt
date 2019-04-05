package com.example.iutsummer.ui.user.aboutstudent

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iutsummer.App
import com.example.iutsummer.R
import com.example.iutsummer.databinding.FragmentAboutStudentBinding

class AboutStudent : Fragment() {

    lateinit var binding:FragmentAboutStudentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_about_student,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this,AboutViewModelFactory(this.activity!!.application as App)).get(AboutStudentViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner=this

        viewModel.student.observe(this, Observer {
            if(it!=null){
                viewModel.initialize(it)
            }
        })

    }
}
