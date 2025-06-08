package com.alqiran.portfoliomainadmin.ui.navigation

import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import kotlinx.serialization.Serializable

@Serializable
data object SplashScreenRoute

@Serializable
data object HomeScreenRoute

@Serializable
data class ProjectItemRoute(val project: ProjectUiModel)

@Serializable
data class ProjectsScreenRoute(val projects: List<ProjectUiModel>)

@Serializable
data class CoursesScreenRoute(val courses: List<CourseUiModel>)

@Serializable
data object MessageScreenRoute


// For Admin

@Serializable
data class TopTitleAdminScreenRoute(val userName: String, val userImage: String?, val jobTitle: String, val accounts: List<ContactAndAccountsUiModel>?, val cvUrl: String?)

