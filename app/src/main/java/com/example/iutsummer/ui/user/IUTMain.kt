package com.example.iutsummer.ui.user

import android.app.DatePickerDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.iutsummer.R
import com.example.iutsummer.databinding.ActivityIutmainBinding
import com.example.iutsummer.ui.main.MainActivity
import com.example.iutsummer.ui.user.news.NewsFragment
import kotlinx.android.synthetic.main.activity_iutmain.*
import kotlinx.android.synthetic.main.activity_iutmain.view.*
import kotlinx.android.synthetic.main.app_bar_iutmain.*

class IUTMain : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var viewModel: IUTMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding:ActivityIutmainBinding=DataBindingUtil.setContentView(this,R.layout.activity_iutmain)
        viewModel = ViewModelProviders.of(this,IUTMainViewModelFactory(application)).get(IUTMainViewModel::class.java)
        binding.viewM = viewModel
        binding.lifecycleOwner=this

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        viewModel.student().observe(this, Observer {
            if(it!=null){
                nav_view.getHeaderView(0).findViewById<TextView>(R.id.nameid).text = it.name
                nav_view.getHeaderView(0).findViewById<TextView>(R.id.emailid).text = it.email
            }else{
                val login = Intent(this,MainActivity::class.java)
                startActivity(login)
                finish()
            }
        })

        nav_view.setNavigationItemSelectedListener(this)
      }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.iutmain, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_request -> {
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.requestForm)
            }
            R.id.nav_youth -> {
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.newsPage)
            }

            R.id.nav_about->{
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.aboutStudent)
            }

            R.id.nav_lostfound->{
                Navigation.findNavController(this,R.id.allcaontainer).navigate(R.id.lostFoundFragment)
            }

            R.id.nav_logout -> {
                viewModel.LogOut()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
