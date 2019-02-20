package com.example.iutsummer.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.iutsummer.R
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.iutsummer.databinding.ActivityMainBinding
import com.example.iutsummer.ui.user.IUTMain
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    val handler = Handler()

    lateinit var animation : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this,MainViewModelFactory(application,binding)).get(MainaActivityViewModel::class.java)
        binding.veiwModel = viewModel
        binding.lifecycleOwner = this
        animation = AnimationUtils.loadAnimation(applicationContext,R.anim.shake)
        splashScreenMethod(binding)
        uiObserves(binding,viewModel)
    }

    /**
     * SplashScreen method
     */
    fun splashScreenMethod(binding:ActivityMainBinding){
        handler.postDelayed({
            binding.root.baseContainer.visibility= View.VISIBLE
            binding.root.logo_image.layoutParams.height=
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160f, this.resources.displayMetrics).toInt()
            val layoutParams = binding.root.logocontainer.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
            binding.root.logocontainer.layoutParams = layoutParams
        },2000)
    }

    /**
     * UI observes to trigger
     */
    fun uiObserves(binding: ActivityMainBinding,viewModel:MainaActivityViewModel){
        viewModel.isIDCorrect.observe(this, Observer {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (it == false) {
                    binding.userId.background = applicationContext.getDrawable(R.drawable.ed_bg_f_red)
                    binding.userId.startAnimation(animation)
                }
                else {
                    binding.userId.background = applicationContext.getDrawable(R.drawable.ed_bg)
                }
            }
        })

        viewModel.isEmailCorrect.observe(this, Observer {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (it == false) {
                    binding.userEmail.background = applicationContext.getDrawable(R.drawable.ed_bg_red)
                    binding.userEmail.startAnimation(animation)
                }
                else {
                    binding.userEmail.background = applicationContext.getDrawable(R.drawable.ed_bgo)
                }
            }
        })

        viewModel.iutStudents.observe(this,Observer{
            if(it!=null){
                Toast.makeText(this,it.Name,Toast.LENGTH_LONG).show()
                val intent = Intent(this,IUTMain::class.java)
                startActivity(intent)
                viewModel.dropStudent()
                this.finish()
            }
        })

        viewModel.isSendEmail.observe(this, Observer {
            if(it == true){
                logocontainer.visibility=View.GONE
                waiting.visibility=View.VISIBLE
                val animate = AnimationUtils.loadAnimation(this,R.anim.bounce)
                waiting.startAnimation(animate)
            }
        })
    }
}
