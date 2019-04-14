package com.example.iutsummer.ui.user.requestform

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.os.AsyncTask
import android.view.View
import android.widget.RadioButton
import com.example.iutsummer.App
import com.example.iutsummer.databinding.FragmentRequestFormBinding
import com.example.iutsummer.utils.ObservableViewModel
import com.example.iutsummer.utils.showMessage
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*


class RequestViewModel(ctx:App,val binding: FragmentRequestFormBinding) : ObservableViewModel(ctx) {

    @Bindable
    var id = MutableLiveData<String>()

    @Bindable
    var name = MutableLiveData<String>()

    @Bindable
    var passport = MutableLiveData<String>()

    //region Sending data

    var success = MutableLiveData<String>()

    fun sendBtn(v: View) {
        var departmentID = binding.department.checkedRadioButtonId

        var copiesID = binding.copies.checkedRadioButtonId

        var yearStudyID = binding.yearStudy.checkedRadioButtonId

        var typeOfLetterID = binding.typeOfStudy.checkedRadioButtonId


        val department = binding.root.findViewById<RadioButton>(departmentID)
        val copies = binding.root.findViewById<RadioButton>(copiesID)
        val yearStudy = binding.root.findViewById<RadioButton>(yearStudyID)
        val typeOfLetter = binding.root.findViewById<RadioButton>(typeOfLetterID)

        val obj = PostData()

        obj.execute(
            id.value.toString(),
            name.value.toString(),
            passport.value.toString(),
            department.text.toString(),
            copies.text.toString(),
            yearStudy.text.toString(),
            typeOfLetter.text.toString()
        )

        success.value = UUID.randomUUID().toString()
        v.showMessage("Successfully send")
    }

    class PostData : AsyncTask<String, Void, Boolean>() {
        override fun doInBackground(vararg params: String?): Boolean {
            //Value Keys

            /**
             * Keys to send by
             */
            val ID_KEY = "entry.1614695099"
            val NAME_KEY = "entry.553312524"
            val PASSPORT_KEY = "entry.1724613617"
            val DEPARTMENT_KEY = "entry.1919022835"
            val COPIES_KEY = "entry.1346382317"
            val YEAR_STUDY_KEY = "entry.2142729193"
            val TYPE_LETTER_KEY = "entry.1731600607"

            val FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8")
            val URL = "https://docs.google.com/forms/d/e/1FAIpQLSf-p5sXhKF3XWMRgvEJ8n1CZ8LYyUNHVni858TGVMxlcahHJA/formResponse"

            var result: Boolean = true

            /**
             * Values to be send
             */
            val id = params[0]
            val name = params[1]
            val passport = params[2]
            val department = params[3]
            val copies = params[4]
            val year = params[5]
            val typeLetter = params[6]

            var postBody: String = ""

            try {
                postBody = ID_KEY + "=" + URLEncoder.encode(id, "UTF-8") +
                        "&" + NAME_KEY + "=" + URLEncoder.encode(name, "UTF-8") +
                        "&" + PASSPORT_KEY + "=" + URLEncoder.encode(passport, "UTF-8") +
                        "&" + DEPARTMENT_KEY + "=" + URLEncoder.encode(department, "UTF-8") +
                        "&" + COPIES_KEY + "=" + URLEncoder.encode(copies, "UTF-8") +
                        "&" + YEAR_STUDY_KEY + "=" + URLEncoder.encode(year, "UTF-8") +
                        "&" + TYPE_LETTER_KEY + "=" + URLEncoder.encode(typeLetter, "UTF-8")
            } catch (exp: UnsupportedEncodingException) {
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
}
    //endregion
