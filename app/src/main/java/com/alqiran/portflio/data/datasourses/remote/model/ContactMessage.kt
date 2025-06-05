package com.alqiran.portflio.data.datasourses.remote.model

import com.alqiran.portflio.utils.Constants.Companion.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ContactMessage (
    val date: String = SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).format(Date()),
    val email: String,
    val message: String
)