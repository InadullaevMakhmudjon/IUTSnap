package com.example.iutsummer.ui.user.requestform

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.iutsummer.App
import com.example.iutsummer.databinding.FragmentRequestFormBinding
import com.example.iutsummer.utils.ObservableViewModel
import com.example.iutsummer.utils.showMessage
import java.util.*


class RequestViewModel(ctx:App,val binding: FragmentRequestFormBinding) : ObservableViewModel(ctx) {


    //region Sending data
/*
    fun clickListen(){
            val checkedID = binding.checkbox.checkedRadioButtonId
            val checkbox = binding.root.findViewById<RadioButton>(checkedID)
            val obj = PostData()
            obj.execute(nameText.value.toString(),checkbox.text.toString())
            nameText.value=""
    }

     class PostData: AsyncTask<String, Void, Boolean>() {
        override fun doInBackground(vararg params: String?): Boolean {
            //Value Keys
            val NAME_KEY = "entry.2016591610"
            val GENDER_KEY="entry.1056762221"
            val FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8")
            val URL = "https://docs.google.com/forms/d/e/1FAIpQLSeKAx7k_w9Rtm8gTXsYb3jV7ngw3SRGPDE7XstheaqB0vfCAw/formResponse"

            var result:Boolean = true

            val name = params[0]
            val gender = params[1]
            var postBody:String=""

            try{
                postBody = NAME_KEY+"=" + URLEncoder.encode(name,"UTF-8") +
                        "&" + GENDER_KEY + "=" + URLEncoder.encode(gender,"UTF-8")
            }catch (exp:UnsupportedEncodingException){
                result = false
            }

            try {
                //Create OkHttpClient for sending request
                val client = OkHttpClient()

                //Create the request body with the help of Media Type
                val body = RequestBody.create(FORM_DATA_TYPE, postBody)
                val request = Request.Builder()
                    .url(URL)
                    .post(body)
                    .build()
                //Send the request
                val response = client.newCall(request).execute()
            } catch (exception: IOException) {
                result = false
            }
            return result
        }

        override fun onPostExecute(result: Boolean?) {

        }
    }
    */
    //endregion

    var success = MutableLiveData<String>()

    fun sendBtn(v: View){
        success.value = UUID.randomUUID().toString()
        v.showMessage("Successfully send")
    }
}