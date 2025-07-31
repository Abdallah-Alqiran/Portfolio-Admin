package com.alqiran.portfoliomainadmin.ui.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContentTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContentUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel
import com.alqiran.portfoliomainadmin.ui.model.UserUiModel
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel

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
                id = exp.id,
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
        },
        contentsTitle = this.contentsTitle?.map { contentTitle ->
            ContentTitleUiModel(
                id = contentTitle.id,
                contentTitle = contentTitle.contentTitle,
                contents = contentTitle.contents.map { content ->
                    ContentUiModel(
                        id = content.id,
                        contentDescription = content.contentDescription,
                        contentUrl = content.contentUrl,
                    )
                }
            )
        },
        certificates = this.certificates?.map { certificate ->
            CertificateUiModel(
                id = certificate.id,
                imageUrl = certificate.imageUrl,
                certificateName = certificate.certificateName,
                description = certificate.description,
            )
        },
        videos = this.videos?.map { video ->
            VideoPresentationUiModel(
                id = video.id,
                videoUrl = video.videoUrl,
                videoTitle = video.videoTitle
            )
        }
    )
}