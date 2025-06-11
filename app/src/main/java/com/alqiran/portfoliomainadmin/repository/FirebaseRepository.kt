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

interface FirebaseRepository {

    suspend fun getAllUserData(): User

    fun sendMessage(contactMessage: ContactMessage)

    fun uploadUserData(user: String, image: String?, job: String, cv: String?)

    fun uploadContactAndAccounts(accounts: List<ContactAndAccounts>)
    fun deleteContactAndAccount(account: ContactAndAccounts)

    fun uploadEducation(education: List<Education>)
    fun deleteEducation(education: Education)

    fun uploadTechnologiesAndTools(technologiesAndTools: List<TechnologyTitle>)
    fun deleteTechnologyAndTool(technologyAndTool: TechnologyTitle)
    fun deleteTechnology(technology: Technology)

    fun uploadSkills(skills: List<Skill>)
    fun deleteSkill(skill: Skill)

    fun uploadProjects(projects: List<Project>)
    fun deleteProject(project: Project)

    fun uploadCourses(courses: List<Course>)
    fun deleteCourse(course: Course)

    fun uploadExperience(experience: List<Experience>)
    fun deleteExperience(experience: Experience)

    fun editAbout(about: String)


    suspend fun getAllMessages(): Flow<List<ContactMessage>>
    fun deleteMessage(message: ContactMessage)
}