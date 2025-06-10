package com.alqiran.portfoliomainadmin.data.datasourses.remote

import android.util.Log
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Course
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Experience
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Skill
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.TechnologyTitle
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User
import com.alqiran.portfoliomainadmin.utils.Constants.Companion.COLLECTION_NAME
import com.alqiran.portfoliomainadmin.utils.Constants.Companion.DOCUMENT_USER_NAME
import com.alqiran.portfoliomainadmin.utils.isOnline
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    val collectionAndDocument = firestore.collection(COLLECTION_NAME).document(DOCUMENT_USER_NAME)

    suspend fun getAllUserData(): User {

        if (!isOnline()) {
            try {
                val localSnapshot = collectionAndDocument
                    .get(Source.CACHE)
                    .await()
                if (localSnapshot.exists()) {
                    val cachedUser = localSnapshot.toObject(User::class.java)
                    if (cachedUser != null) {
                        Log.d("Al-qiran", "From Cashed Data")
                        return cachedUser
                    }
                }
            } catch (e: Exception) {
                Log.d("Al-qiran", "Cached Exception ${e.message.toString()}")
            }
        }

        try {
            val serverSnapshot = collectionAndDocument
                .get(Source.SERVER)
                .await()

            if (serverSnapshot.exists()) {
                val serverUser = serverSnapshot.toObject(User::class.java)
                if (serverUser != null) {
                    Log.d("Al-qiran", "From server Data")

                    return serverUser
                } else {
                    throw IllegalStateException("Server user data is corrupted or null")
                }
            } else {
                throw NoSuchElementException("No user data exists on the server")
            }
        } catch (serverException: Exception) {
            Log.d("Al-qiran", "Server Exception ${serverException.message.toString()}")

            throw serverException
        }
    }

    fun sendMessage(contactMessage: ContactMessage) {
        try {
            collectionAndDocument
                .update("contactMessage", FieldValue.arrayUnion(contactMessage))
                .addOnFailureListener {
                    throw Exception("There is Error sending message")
                }
        } catch (e: Exception) {
            throw e
        }
    }

    fun uploadUserData(userName: String, imageUrl: String?, jobTitle: String, cvUrl: String?) {
        val data = mapOf(
            "userName" to userName,
            "userImage" to (imageUrl?: ""),
            "jobTitle" to jobTitle,
            "cvUrl" to (cvUrl?: "")
        )
        try {
            collectionAndDocument
                .set(data, SetOptions.merge())
                .addOnFailureListener { exception ->
                    throw Exception("There is Error Uploading user data  ${exception.message}")
                }
        } catch (e: Exception) {
            throw e
        }
    }

    fun uploadContactAndAccounts(accounts: List<ContactAndAccounts>) {

        firestore.runTransaction {  transaction ->
            val exist = transaction.get(collectionAndDocument).get("contactsAndAccounts") as? List<Map<String, Any>> ?: emptyList()

            val current = exist.map {
                ContactAndAccounts(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    webName = it["webName"] as? String ?: "",
                    url = it["url"] as? String ?: ""
                )
            }.toMutableList()

            accounts.forEach { new ->
                val existingIndex = current.indexOfFirst { it.id == new.id }
                if (existingIndex != -1) {
                    current[existingIndex] = new
                } else {
                    current.add(new)
                }
            }

            current.sortBy { it.id }
            transaction.update(collectionAndDocument, "contactAndAccounts", current)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating contacts: ${exception.message}")
        }
    }
    fun deleteContactAndAccount(account: ContactAndAccounts) {
        collectionAndDocument.update("contactAndAccounts", FieldValue.arrayRemove(account))
            .addOnFailureListener { exception ->
                throw Exception("Error Deleting Account: ${exception.message}")
            }
    }



    fun uploadEducation(education: List<Education>) {
        firestore.runTransaction {  transaction ->
            val exist = transaction.get(collectionAndDocument).get("education") as? List<Map<String, Any>> ?: emptyList()

            val current = exist.map {
                Education(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    university = it["university"] as? String ?: "",
                    date = it["date"] as? String ?: "",
                    major = it["major"] as? String ?: ""
                )
            }.toMutableList()

            education.forEach { new ->
                val existingIndex = current.indexOfFirst { it.id == new.id }
                if (existingIndex != -1) {
                    current[existingIndex] = new
                } else {
                    current.add(new)
                }
            }

            current.sortBy { it.id }
            transaction.update(collectionAndDocument, "education", current)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating Education: ${exception.message}")
        }
    }
    fun deleteEducation(education: Education) {
        collectionAndDocument.update("education", FieldValue.arrayRemove(education))
            .addOnFailureListener { exception ->
                throw Exception("Error Deleting Education: ${exception.message}")
            }
    }


    fun uploadTechnologiesAndTools(technologiesAndTools: List<TechnologyTitle>) {
        TODO("Not yet implemented")
    }
    fun deleteTechnologyAndTool(technologyAndTool: TechnologyTitle) {
        TODO("Not yet implemented")
    }

    fun uploadSkills(skills: List<Skill>) {
        TODO("Not yet implemented")
    }
    fun deleteSkill(skill: Skill) {
        TODO("Not yet implemented")
    }

    fun uploadProjects(projects: List<Project>) {
        TODO("Not yet implemented")
    }
    fun deleteProject(project: Project) {
        TODO("Not yet implemented")
    }

    fun uploadCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }
    fun deleteCourse(course: Course) {
        TODO("Not yet implemented")
    }

    fun uploadExperience(experience: List<Experience>) {
        TODO("Not yet implemented")
    }
    fun deleteExperience(experience: Experience) {
        TODO("Not yet implemented")
    }

    fun editAbout(about: String) {
        collectionAndDocument.update("about", about)
            .addOnFailureListener { exception ->
                throw Exception("Error Editing About: ${exception.message}")
            }
    }

}
