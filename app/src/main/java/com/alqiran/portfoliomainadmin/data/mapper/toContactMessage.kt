package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.ui.model.ContactMessageUiModel


fun ContactMessageUiModel.toContactMessage(): ContactMessage {
    return ContactMessage(
        date = this.date,
        email = this.email,
        message = this.message
    )
}
