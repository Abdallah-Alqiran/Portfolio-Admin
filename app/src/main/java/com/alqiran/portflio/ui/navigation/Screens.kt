package com.alqiran.portflio.ui.navigation

sealed class Screens(val route: String) {
    data object SplashScreenRoute: Screens("splash")

    data object HomeScreenRoute: Screens("home")

    data object ProjectItemRoute: Screens("project_item/{project_id}") {
        fun passId(projectId: String): String {
            return "project_item/$projectId"
        }
    }

    data object ProjectsScreenRoute: Screens("projects")
    data object CoursesScreenRoute: Screens("courses")

    data object MessageScreenRoute: Screens("message")
}