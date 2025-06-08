package com.alqiran.portfoliomainadmin.domain.repository

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User

interface FirebaseRepository {

    suspend fun getAllUserData(): User

    fun sendMessage(contactMessage: ContactMessage)
}