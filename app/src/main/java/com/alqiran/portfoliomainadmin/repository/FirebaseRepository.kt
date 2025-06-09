package com.alqiran.portfoliomainadmin.repository

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User

interface FirebaseRepository {

    suspend fun getAllUserData(): User

    fun sendMessage(contactMessage: ContactMessage)

    fun uploadUserData(user: String, image: String?, job: String, cv: String?)

    fun uploadContactAndAccounts(accounts: List<ContactAndAccounts>)
}