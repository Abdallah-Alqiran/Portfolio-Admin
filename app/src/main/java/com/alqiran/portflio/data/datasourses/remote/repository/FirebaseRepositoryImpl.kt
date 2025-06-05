package com.alqiran.portflio.data.datasourses.remote.repository

import com.alqiran.portflio.data.datasourses.remote.RemoteDataSource
import com.alqiran.portflio.data.datasourses.remote.model.ContactMessage
import com.alqiran.portflio.data.datasourses.remote.model.Course
import com.alqiran.portflio.data.datasourses.remote.model.Project
import com.alqiran.portflio.data.datasourses.remote.model.User
import com.alqiran.portflio.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): FirebaseRepository{

    override suspend fun getAllUserData(): User {
        return remoteDataSource.getAllUserData()
    }

    override suspend fun getAllProjects(): List<Project> {
        return remoteDataSource.getAllProjects()
    }

    override suspend fun getAllCourses(): List<Course> {
        return remoteDataSource.getAllCourses()
    }

    override suspend fun getProjectItem(id: Int): Project {
        return remoteDataSource.getProjectItem(id)
    }

    override fun sendMessage(contactMessage: ContactMessage) {
        return remoteDataSource.sendMessage(contactMessage)
    }
}