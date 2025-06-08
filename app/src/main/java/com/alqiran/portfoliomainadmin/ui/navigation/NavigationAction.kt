package com.alqiran.portfoliomainadmin.ui.navigation

import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

sealed class NavigationAction {

    object Nothing: NavigationAction()

    data class ToProject(val project: ProjectUiModel): NavigationAction()

    data class ToViewAllProjects(val projects: List<ProjectUiModel>): NavigationAction()
    data class ToViewAllCourses(val courses: List<CourseUiModel>): NavigationAction()


}