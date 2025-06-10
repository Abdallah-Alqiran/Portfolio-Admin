package com.alqiran.portfoliomainadmin.ui.navigation

import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel

sealed class NavigationAction {

    object Nothing : NavigationAction()

    data class ToProject(val project: ProjectUiModel) : NavigationAction()

    data class ToViewAllProjects(val projects: List<ProjectUiModel>) : NavigationAction()
    data class ToViewAllCourses(val courses: List<CourseUiModel>) : NavigationAction()


    // Edit Section
    data class ToTopTitleEdit(val userName: String, val userImage: String?, val jobTitle: String, val accounts: List<ContactAndAccountsUiModel>?, val cvUrl: String?) : NavigationAction()

    data class ToAboutEdit(val about: String) : NavigationAction()

    data class ToEducationEdit(val educations: List<EducationUiModel>): NavigationAction()

    data class ToTechnologiesAndToolsEdit(val technologiesAndTools: List<TechnologyTitleUiModel>) : NavigationAction()

    data class ToSkillsEdit(val skills: List<SkillUiModel>) : NavigationAction()

    data class ToProjectsEdit(val projects: List<ProjectUiModel>) : NavigationAction()

    data class ToCoursesEdit(val courses: List<CourseUiModel>) : NavigationAction()

    data class ToExperienceEdit(val experience: List<ExperienceUiModel>) : NavigationAction()


}