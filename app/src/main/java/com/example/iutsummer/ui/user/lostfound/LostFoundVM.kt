package com.example.iutsummer.ui.user.lostfound

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.iutsummer.App
import com.example.iutsummer.R
import com.example.iutsummer.databinding.DialogLostfounditemsBinding
import com.example.iutsummer.ui.user.lostfound.dialog.DialogLFVMFactory
import com.example.iutsummer.ui.user.lostfound.dialog.DialogLFViewModel
import com.example.iutsummer.utils.ObservableViewModel
import kotlinx.android.synthetic.main.dialog_lostfounditems.view.*

class LostFoundVM(val app:App,val parent:LostFoundFragment):ObservableViewModel(app) {

    private val repository = LostFoundRepository(app)
    lateinit var binding:DialogLostfounditemsBinding

    /**
     * All Items
     */
    var allItems = repository.allItems

    /**
     * Floating Action button click event
     * Dialog's server response observes here do dismiss it
     */
    fun postItem(v:View){
        val dialog = Dialog(v.context)
         binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.dialog_lostfounditems,null,false)

        val viewModel = ViewModelProviders.of(parent,DialogLFVMFactory(app,parent,binding.root.captured))[DialogLFViewModel::class.java]
        binding.viewModelFragment = viewModel
        binding.lifecycleOwner = parent

        /**
         * Dialog dismisses when response comes from firebase
         */
        binding.viewModelFragment!!.response.observe(parent, Observer{
            dialog.dismiss()
        })

        /**
         * Cancel onClick event
         */
        binding.cancelAction.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(binding.root)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.show()
        Log.d("MakhmudjonHello", "btn: $binding")
    }

    /**
     * Calls setImage(v:ImageVIew) inside Dialog, and changes dialog image
     */
    fun setImage(){
        binding.viewModelFragment!!.setImage(binding.root.captured)
        Log.d("MakhmudjonHello", "Captuured: $binding")
    }

    /**
     * Gets all data from cloud
     */
    fun getAllItems() = repository.getAllItems()

}