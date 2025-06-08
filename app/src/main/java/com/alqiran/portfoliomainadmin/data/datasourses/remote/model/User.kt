package com.alqiran.portfoliomainadmin.data.datasourses.remote.model


data class User(
    val userName: String = "",
    val jobTitle: String = "",
    val userImage: String? = null,
    val about: String? = null,
    val cvUrl: String? = null,
    val projects: List<Project>? = null,
    val contactAndAccounts: List<ContactAndAccounts>? = null,
    val skills: List<Skill>? = null,
    val technologiesAndTools: List<TechnologyTitle>? = null,
    val courses: List<Course>? = null,
    val experiences: List<Experience>? = null,
    val education: List<Education>? = null
)
