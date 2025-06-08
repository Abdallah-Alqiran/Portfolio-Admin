package com.alqiran.portfoliomainadmin.utils

import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.UserUiModel
import com.alqiran.portfoliomainadmin.utils.Constants.Companion.COLLECTION_NAME
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


fun UserUiModel.toMap(): Map<String, Any?> {
    return mapOf(
        "userName" to userName,
        "jobTitle" to jobTitle,
        "userImage" to userImage,
        "about" to about,
        "cvUrl" to cvUrl,
        "projects" to projects?.map { project ->
            mapOf(
                "id" to project.id,
                "image" to project.image,
                "projectName" to project.projectName,
                "description" to project.description,
                "url" to project.url
            )
        },
        "contactAndAccounts" to contactAndAccounts?.map { contact ->
            mapOf(
                "id" to contact.id,
                "webName" to contact.webName,
                "url" to contact.url
            )
        },
        "skills" to skills?.map { skill ->
            mapOf(
                "id" to skill.id,
                "skillName" to skill.skillName
            )
        },
        "technologiesAndTools" to technologiesAndTools?.map { techTitle ->
            mapOf(
                "id" to techTitle.id,
                "technologyTitle" to techTitle.technologyTitle,
                "technologies" to techTitle.technologies.map { tech ->
                    mapOf(
                        "id" to tech.id,
                        "technologyName" to tech.technologyName
                    )
                }
            )
        },
        "courses" to courses?.map { course ->
            mapOf(
                "id" to course.id,
                "courseName" to course.courseName,
                "courseDescription" to course.courseDescription
            )
        },
        "experiences" to experiences?.map { exp ->
            mapOf(
                "experienceTitle" to exp.experienceTitle,
                "company" to exp.company,
                "date" to exp.date,
                "description" to exp.description
            )
        },
        "education" to education?.map { edu ->
            mapOf(
                "id" to edu.id,
                "university" to edu.university,
                "date" to edu.date,
                "major" to edu.major
            )
        }
    )
}

fun uploadContact() {
    val contact = ContactAndAccountsUiModel(
        id = 4,
        webName = "leetcode",
        url = "https://leetcode.com/u/Abdallah_Alqiran/"
    )
    val db = FirebaseFirestore.getInstance()
    val userRef = db.collection(COLLECTION_NAME).document(Constants.DOCUMENT_USER_NAME)
    userRef.update("contactAndAccounts", FieldValue.arrayUnion(contact))
}

fun uploadAllUserData(user: UserUiModel, document: String) {
    val db = FirebaseFirestore.getInstance()
    val userRef = db.collection(COLLECTION_NAME).document(document)
    userRef.set(user.toMap())
}