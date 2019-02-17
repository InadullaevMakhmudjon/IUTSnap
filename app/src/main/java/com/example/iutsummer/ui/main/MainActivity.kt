package com.example.iutsummer.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.iutsummer.R
import com.example.iutsummer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import android.widget.RelativeLayout



class MainActivity : AppCompatActivity() {
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this,MainViewModelFactory(application)).get(MainaActivityViewModel::class.java)
        binding.veiwModel = viewModel
        binding.lifecycleOwner = this
        handler.postDelayed({
            binding.root.baseContainer.visibility= View.VISIBLE
            binding.root.logo_image.layoutParams.height=
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160f, this.resources.displayMetrics).toInt()
            val layoutParams = binding.root.logocontainer.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
            binding.root.logocontainer.layoutParams = layoutParams
        },2000)
    }
}
