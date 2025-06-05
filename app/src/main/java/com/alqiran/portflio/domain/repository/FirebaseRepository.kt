package com.alqiran.portflio.domain.repository

import com.alqiran.portflio.data.datasourses.remote.model.ContactMessage
import com.alqiran.portflio.data.datasourses.remote.model.Course
import com.alqiran.portflio.data.datasourses.remote.model.Project
import com.alqiran.portflio.data.datasourses.remote.model.User

interface FirebaseRepository {

    suspend fun getAllUserData(): User

    suspend fun getAllProjects(): List<Project>

    suspend fun getAllCourses(): List<Course>

    suspend fun getProjectItem(id: Int): Project

    fun sendMessage(contactMessage: ContactMessage)
}