package com.alqiran.portfoliomainadmin.ui.navigation

import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

sealed class NavigationAction {

    object Nothing : NavigationAction()

    data class ToProject(val project: ProjectUiModel) : NavigationAction()

    data class ToViewAllProjects(val projects: List<ProjectUiModel>) : NavigationAction()
    data class ToViewAllCourses(val courses: List<CourseUiModel>) : NavigationAction()


    // Edit Section
    data class ToTopTitleEdit(val userName: String, val userImage: String?, val jobTitle: String, val accounts: List<ContactAndAccountsUiModel>?) : NavigationAction()


}