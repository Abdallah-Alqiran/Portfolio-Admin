package com.alqiran.portflio.ui.navigation

import com.alqiran.portflio.ui.model.CourseUiModel
import com.alqiran.portflio.ui.model.ProjectUiModel

sealed class NavigationAction {

    object Nothing: NavigationAction()

    data class ToProject(val projectId: String): NavigationAction()

    data class ToViewAllProjects(val projects: List<ProjectUiModel>): NavigationAction()
    data class ToViewAllCourses(val courses: List<CourseUiModel>): NavigationAction()


}