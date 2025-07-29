package com.alqiran.portfoliomainadmin.ui.navigation

import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContentTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel
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

@Serializable
data class AboutAdminScreenRoute(val about: String?)

@Serializable
data class EducationAdminScreenRoute(val educations: List<EducationUiModel>?)

@Serializable
data class TechnologiesAndToolsAdminScreenRoute(val technologiesAndTools: List<TechnologyTitleUiModel>?)

@Serializable
data class SkillsAdminScreenRoute(val skills: List<SkillUiModel>?)

@Serializable
data class ProjectsAdminScreenRoute(val projects: List<ProjectUiModel>?)

@Serializable
data class CoursesAdminScreenRoute(val courses: List<CourseUiModel>?)

@Serializable
data class ExperienceAdminScreenRoute(val experience: List<ExperienceUiModel>?)

@Serializable
data class ContentAdminScreenRoute(val contentsTitle: List<ContentTitleUiModel>)

@Serializable
data class CertificateAdminScreenRoute(val certificates: List<CertificateUiModel>)

@Serializable
data class VideoAdminScreenRoute(val videos: List<VideoPresentationUiModel>)
