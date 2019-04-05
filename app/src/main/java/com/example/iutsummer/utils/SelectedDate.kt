package com.example.iutsummer.utils

import java.util.*

object SelectedDate {
    val c = Calendar.getInstance()
    var year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    var Day = c.get(Calendar.DAY_OF_MONTH)
}