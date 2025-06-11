package com.alqiran.portfoliomainadmin.ui.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.ui.model.ContactMessageUiModel


fun List<ContactMessage>.toContactMessageDataUi(): List<ContactMessageUiModel> {
    return this.map { item ->
        ContactMessageUiModel(
            date = item.date,
            email = item.email,
            message = item.message
        )
    }
}