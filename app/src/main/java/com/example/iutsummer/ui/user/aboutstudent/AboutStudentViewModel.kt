package com.example.iutsummer.ui.user.aboutstudent

import android.arch.lifecycle.MutableLiveData
import com.example.iutsummer.App
import com.example.iutsummer.data.db.entity.Student
import com.example.iutsummer.utils.ObservableViewModel

class AboutStudentViewModel(val ctx: App):ObservableViewModel(ctx) {
    private val repository = AboutRepository(ctx)
    var student = repository.student

    val studentID = MutableLiveData<String>()
    val studentName = MutableLiveData<String>()
    val studentYear = MutableLiveData<String>()
    val studentDepartment = MutableLiveData<String>()
    val studentYearAlias = MutableLiveData<String>()
    val studentEmail = MutableLiveData<String>()

    fun initialize(st:Student){
        studentID.value = st.id
        studentName.value = st.name
        studentYear.value = st.year
        studentDepartment.value = st.department
        studentYearAlias.value = st.yearAlias
        studentEmail.value = st.email

    }
}