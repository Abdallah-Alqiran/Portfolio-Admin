package com.alqiran.portflio.ui.mapper

import com.alqiran.portflio.data.datasourses.remote.model.User
import com.alqiran.portflio.ui.model.ContactAndAccountsUiModel
import com.alqiran.portflio.ui.model.CourseUiModel
import com.alqiran.portflio.ui.model.EducationUiModel
import com.alqiran.portflio.ui.model.ExperienceUiModel
import com.alqiran.portflio.ui.model.ProjectUiModel
import com.alqiran.portflio.ui.model.SkillUiModel
import com.alqiran.portflio.ui.model.TechnologyUiModel
import com.alqiran.portflio.ui.model.TechnologyTitleUiModel
import com.alqiran.portflio.ui.model.UserUiModel

fun User.toUserDataUi(): UserUiModel {
    return UserUiModel(
        userName = this.userName,
        jobTitle = this.jobTitle,
        userImage = this.userImage,
        about = this.about,
        cvUrl = this.cvUrl,
        projects = this.projects?.map { project ->
            ProjectUiModel(
                id = project.id,
                image = project.image,
                projectName = project.projectName,
                description = project.description,
                url = project.url
            )
        },
        contactAndAccounts = this.contactAndAccounts?.map { contact ->
            ContactAndAccountsUiModel(
                id = contact.id,
                webName = contact.webName,
                url = contact.url
            )
        },
        skills = this.skills?.map { skill ->
            SkillUiModel(
                id = skill.id,
                skillName = skill.skillName
            )
        },
        technologiesAndTools = this.technologiesAndTools?.map { techTitle ->
            TechnologyTitleUiModel(
                id = techTitle.id,
                technologyTitle = techTitle.technologyTitle,
                technologies = techTitle.technologies.map { tech ->
                    TechnologyUiModel(
                        id = tech.id,
                        technologyName = tech.technologyName
                    )
                }
            )
        },
        courses = this.courses?.map { course ->
            CourseUiModel(
                id = course.id,
                courseName = course.courseName,
                courseDescription = course.courseDescription
            )
        },
        experiences = this.experiences?.map { exp ->
            ExperienceUiModel(
                experienceTitle = exp.experienceTitle,
                company = exp.company,
                date = exp.date,
                description = exp.description
            )
        },
        education = this.education?.map { edu ->
            EducationUiModel(
                id = edu.id,
                university = edu.university,
                date = edu.date,
                major = edu.major
            )
        }
    )
}