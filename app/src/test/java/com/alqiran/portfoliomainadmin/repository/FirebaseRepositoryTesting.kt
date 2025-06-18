package com.alqiran.portfoliomainadmin.repository

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Course
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Experience
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Skill
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Technology
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.TechnologyTitle
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking

class FirebaseRepositoryTesting : FirebaseRepository {

    private val _userData = MutableStateFlow(User())
    private val _allMessages = MutableStateFlow<List<ContactMessage>>(emptyList())

    private var shortUserData = UserData()
    private val contactMessages = mutableListOf<ContactMessage>()
    private val contactAndAccounts = mutableListOf<ContactAndAccounts>()
    private val educations = mutableListOf<Education>()
    private val technologiesAndTools = mutableListOf<TechnologyTitle>()
    private val skills = mutableListOf<Skill>()
    private val projects = mutableListOf<Project>()
    private val courses = mutableListOf<Course>()
    private val experience = mutableListOf<Experience>()
    
    override suspend fun getAllUserData(): User {
        return _userData.value
    }

    private fun refreshUserData() = runBlocking {
        _userData.value = User(
            userName = shortUserData.user,
            jobTitle = shortUserData.job,
            userImage = shortUserData.image,
            about = shortUserData.about,
            cvUrl = shortUserData.cv,
            projects = projects,
            contactAndAccounts = contactAndAccounts,
            skills = skills,
            technologiesAndTools = technologiesAndTools,
            courses = courses,
            experiences = experience,
            education = educations
        )
        getAllUserData()
    }

    private fun refreshMessages() = runBlocking {
        _allMessages.value = contactMessages
        getAllMessages()
    }

    override fun sendMessage(contactMessage: ContactMessage) {
        contactMessages.add(contactMessage)
        refreshMessages()
    }

    override fun uploadUserData(
        user: String,
        image: String?,
        job: String,
        cv: String?
    ) {

        refreshUserData()
    }

    override fun uploadContactAndAccounts(accounts: List<ContactAndAccounts>) {
        accounts.forEach { account ->
            contactAndAccounts.add(account)
        }
        refreshUserData()
    }

    override fun deleteContactAndAccount(account: ContactAndAccounts) {
        contactAndAccounts.remove(account)
        refreshUserData()
    }

    override fun uploadEducation(education: List<Education>) {
        education.forEach { edu ->
            educations.add(edu)
        }
        refreshUserData()
    }

    override fun deleteEducation(education: Education) {
        educations.remove(education)
    }

    override fun uploadTechnologiesAndTools(technologiesAndTools: List<TechnologyTitle>) {
        technologiesAndTools.forEach { technology ->
            this.technologiesAndTools.add(technology)
        }
        refreshUserData()
    }

    override fun deleteTechnologyAndTool(technologyAndTool: TechnologyTitle) {
        technologiesAndTools.remove(technologyAndTool)
        refreshUserData()
    }

    override fun deleteTechnology(technology: Technology) {

        refreshUserData()
    }

    override fun uploadSkills(skills: List<Skill>) {
        skills.forEach { skill ->
            this.skills.add(skill)
        }
        refreshUserData()
    }

    override fun deleteSkill(skill: Skill) {
        skills.remove(skill)
        refreshUserData()
    }

    override fun uploadProjects(projects: List<Project>) {
        projects.forEach { project ->

        }
        refreshUserData()
    }

    override fun deleteProject(project: Project) {

        refreshUserData()
    }

    override fun uploadCourses(courses: List<Course>) {
        refreshUserData()
    }

    override fun deleteCourse(course: Course) {
        refreshUserData()
    }

    override fun uploadExperience(experience: List<Experience>) {
        refreshUserData()
    }

    override fun deleteExperience(experience: Experience) {
        refreshUserData()
    }

    override fun editAbout(about: String) {
        refreshUserData()
    }

    override suspend fun getAllMessages(): Flow<List<ContactMessage>> {
        return _allMessages
    }

    override fun deleteMessage(message: ContactMessage) {

        refreshMessages()
    }
}

data class UserData(
    val user: String = "",
    val about: String = "",
    val image: String? = "",
    val job: String = "",
    val cv: String? = "",
)
