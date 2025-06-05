package com.alqiran.portflio.ui.mapper

import com.alqiran.portflio.data.datasourses.remote.model.ContactMessage
import com.alqiran.portflio.ui.model.ContactMessageUiModel

fun ContactMessageUiModel.toContactMessage(): ContactMessage {
    return ContactMessage(
        date = this.date,
        email = this.email,
        message = this.message
    )
}