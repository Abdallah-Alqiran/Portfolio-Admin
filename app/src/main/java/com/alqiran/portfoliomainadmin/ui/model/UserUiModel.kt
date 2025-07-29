package com.alqiran.portfoliomainadmin.ui.model

data class UserUiModel(
    val userName: String = "",
    val jobTitle: String = "",
    val userImage: String? = null,
    val about: String? = null,
    val cvUrl: String? = null,
    val projects: List<ProjectUiModel>? = null,
    val contactAndAccounts: List<ContactAndAccountsUiModel>? = null,
    val skills: List<SkillUiModel>? = null,
    val technologiesAndTools: List<TechnologyTitleUiModel>? = null,
    val courses: List<CourseUiModel>? = null,
    val experiences: List<ExperienceUiModel>? = null,
    val education: List<EducationUiModel>? = null,
    val contentsTitle: List<ContentTitleUiModel>? = null,
    val certificates: List<CertificateUiModel>? = null,
    val videos: List<VideoPresentationUiModel>? = null
)
