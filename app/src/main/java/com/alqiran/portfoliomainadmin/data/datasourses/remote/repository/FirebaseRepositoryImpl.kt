package com.alqiran.portfoliomainadmin.data.datasourses.remote.repository

import com.alqiran.portfoliomainadmin.data.datasourses.remote.RemoteDataSource
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
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow
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
    override fun deleteContactAndAccount(account: ContactAndAccounts) {
        return remoteDataSource.deleteContactAndAccount(account)
    }

    override fun uploadEducation(education: List<Education>) {
        return remoteDataSource.uploadEducation(education)
    }
    override fun deleteEducation(education: Education) {
        return remoteDataSource.deleteEducation(education)
    }

    override fun uploadTechnologiesAndTools(technologiesAndTools: List<TechnologyTitle>) {
        return remoteDataSource.uploadTechnologiesAndTools(technologiesAndTools)
    }
    override fun deleteTechnologyAndTool(technologyAndTool: TechnologyTitle) {
        return remoteDataSource.deleteTechnologyAndTool(technologyAndTool)
    }
    override fun deleteTechnology(technology: Technology) {
        return remoteDataSource.deleteTechnology(technology)
    }

    override fun uploadSkills(skills: List<Skill>) {
        return remoteDataSource.uploadSkills(skills)
    }
    override fun deleteSkill(skill: Skill) {
        return remoteDataSource.deleteSkill(skill)
    }

    override fun uploadProjects(projects: List<Project>) {
        return remoteDataSource.uploadProjects(projects)
    }
    override fun deleteProject(project: Project) {
        return remoteDataSource.deleteProject(project)
    }

    override fun uploadCourses(courses: List<Course>) {
        return remoteDataSource.uploadCourses(courses)
    }
    override fun deleteCourse(course: Course) {
        return remoteDataSource.deleteCourse(course)
    }

    override fun uploadExperience(experience: List<Experience>) {
        return remoteDataSource.uploadExperience(experience)
    }
    override fun deleteExperience(experience: Experience) {
        return remoteDataSource.deleteExperience(experience)
    }

    override fun editAbout(about: String) {
        return remoteDataSource.editAbout(about)
    }

    override suspend fun getAllMessages(): Flow<List<ContactMessage>> {
        return remoteDataSource.getAllMessages()
    }
    override fun deleteMessage(message: ContactMessage) {
        return remoteDataSource.deleteMessage(message)
    }
}