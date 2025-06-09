package com.alqiran.portfoliomainadmin.data.datasourses.remote.repository

import com.alqiran.portfoliomainadmin.data.datasourses.remote.RemoteDataSource
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): FirebaseRepository{

    override suspend fun getAllUserData(): User {
        return remoteDataSource.getAllUserData()
    }

    override fun sendMessage(contactMessage: ContactMessage) {
        return remoteDataSource.sendMessage(contactMessage)
    }

    override fun uploadUserData(
        user: String,
        image: String?,
        job: String,
        cv: String?
    ) {
        return remoteDataSource.uploadUserData(user, image, job, cv)
    }

    override fun uploadContactAndAccounts(accounts: List<ContactAndAccounts>) {
        return remoteDataSource.uploadContactAndAccounts(accounts)
    }
}