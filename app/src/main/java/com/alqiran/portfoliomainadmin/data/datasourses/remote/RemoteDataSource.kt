package com.alqiran.portfoliomainadmin.data.datasourses.remote

import android.util.Log
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Course
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Experience
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Skill
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Technology
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.TechnologyTitle
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User
import com.alqiran.portfoliomainadmin.utils.Constants.Companion.COLLECTION_NAME
import com.alqiran.portfoliomainadmin.utils.Constants.Companion.DOCUMENT_USER_NAME
import com.alqiran.portfoliomainadmin.utils.isOnline
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.Source
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private val collectionAndDocument =
        firestore.collection(COLLECTION_NAME).document(DOCUMENT_USER_NAME)


    private fun deleteElement(fieldName: String, type: Any) {
        collectionAndDocument.update(fieldName, FieldValue.arrayRemove(type))
            .addOnFailureListener { exception ->
                throw Exception("Error Delete: ${exception.message}")
            }
    }

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
                    Log.d("Al-qiran", "From server Data ${serverUser.experiences}")

                    return serverUser
                } else {
                    throw IllegalStateException("Server user data is corrupted or null")
                }
            } else {
                val newUser = User()
                collectionAndDocument.set(newUser).await()
                return newUser
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
            "userImage" to (imageUrl ?: ""),
            "jobTitle" to jobTitle,
            "cvUrl" to (cvUrl ?: "")
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

        firestore.runTransaction { transaction ->
            val exist = transaction.get(collectionAndDocument)
                .get("contactsAndAccounts") as? List<Map<String, Any>> ?: emptyList()

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
        deleteElement("contactAndAccounts", account)
    }


    fun uploadEducation(education: List<Education>) {
        firestore.runTransaction { transaction ->
            val exist =
                transaction.get(collectionAndDocument).get("education") as? List<Map<String, Any>>
                    ?: emptyList()

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
        deleteElement("education", education)
    }

    fun uploadTechnologiesAndTools(technologiesAndTools: List<TechnologyTitle>) {
        firestore.runTransaction { transaction ->
            val exist =
                transaction.get(collectionAndDocument).get("technologiesAndTools") as? List<Map<String, Any>>
                    ?: emptyList()

            val current = exist.map {
                TechnologyTitle(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    technologyTitle = it["technologyTitle"] as? String ?: "",
                    technologies = (it["technologies"] as? List<Map<String, Any>>)?.map { tech ->
                        Technology(
                            id = (tech["id"] as? Long)?.toInt()?: 0,
                            technologyName = tech["technologyName"] as? String ?: ""
                        )
                    }?: emptyList()
                )
            }.toMutableList()

            technologiesAndTools.forEach { new ->
                val sortedNew = new.copy(
                    technologies = new.technologies.sortedBy { it.id }
                )

                val existingIndex = current.indexOfFirst { it.id == sortedNew.id }
                if (existingIndex != -1) {
                    current[existingIndex] = sortedNew
                } else {
                    current.add(sortedNew)
                }
            }

            current.sortBy { it.id }
            transaction.update(collectionAndDocument, "technologiesAndTools", current)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating Technologies: ${exception.message}")
        }
    }

    fun deleteTechnologyAndTool(technologyAndTool: TechnologyTitle) {
        deleteElement("technologiesAndTools", technologyAndTool)
    }
    fun deleteTechnology(technology: Technology) {

        firestore.runTransaction { transaction ->
            val doc = transaction.get(collectionAndDocument)
            val data = doc.data?.toMutableMap() ?: mutableMapOf()
            val technologiesAndTools = data["technologiesAndTools"] as? MutableList<MutableMap<String, Any>> ?: mutableListOf()

            technologiesAndTools.forEach { group ->
                val technologies = group["technologies"] as? MutableList<MutableMap<String, Any>> ?: mutableListOf()
                technologies.removeIf { tech ->
                    (tech["id"] as? Long)?.toInt() == technology.id &&
                            tech["technologyName"] as? String == technology.technologyName
                }
            }

            transaction.update(collectionAndDocument, data)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error Deleting Technologies: ${exception.message}")
        }

    }

    fun uploadSkills(skills: List<Skill>) {
        firestore.runTransaction { transaction ->
            val exist =
                transaction.get(collectionAndDocument).get("skills") as? List<Map<String, Any>>
                    ?: emptyList()

            val current = exist.map {
                Skill(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    skillName = it["skillName"] as? String ?: "",
                )
            }.toMutableList()

            skills.forEach { new ->
                val existingIndex = current.indexOfFirst { it.id == new.id }
                if (existingIndex != -1) {
                    current[existingIndex] = new
                } else {
                    current.add(new)
                }
            }

            current.sortBy { it.id }
            transaction.update(collectionAndDocument, "skills", current)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating Skills: ${exception.message}")
        }
    }

    fun deleteSkill(skill: Skill) {
        deleteElement("skills", skill)
    }

    fun uploadProjects(projects: List<Project>) {
        firestore.runTransaction { transaction ->
            val exist =
                transaction.get(collectionAndDocument).get("projects") as? List<Map<String, Any>>
                    ?: emptyList()

            val current = exist.map {
                Project(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    image = (it["image"] as? String) ?: "",
                    projectName = (it["projectName"] as? String) ?: "",
                    description = (it["description"] as? String) ?: "",
                    url = (it["url"] as? String) ?: ""
                )
            }.toMutableList()

            projects.forEach { new ->
                val existingIndex = current.indexOfFirst { it.id == new.id }
                if (existingIndex != -1) {
                    current[existingIndex] = new
                } else {
                    current.add(new)
                }
            }

            current.sortBy { it.id }
            transaction.update(collectionAndDocument, "projects", current)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating Projects: ${exception.message}")
        }
    }

    fun deleteProject(project: Project) {
        deleteElement("projects", project)
    }

    fun uploadCourses(courses: List<Course>) {
        firestore.runTransaction { transaction ->
            val exist =
                transaction.get(collectionAndDocument).get("courses") as? List<Map<String, Any>>
                    ?: emptyList()

            val current = exist.map {
                Course(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    courseName = (it["courseName"] as? String) ?: "",
                    courseDescription = (it["courseDescription"] as? String) ?: ""
                )
            }.toMutableList()

            courses.forEach { new ->
                val existingIndex = current.indexOfFirst { it.id == new.id }
                if (existingIndex != -1) {
                    current[existingIndex] = new
                } else {
                    current.add(new)
                }
            }

            current.sortBy { it.id }
            transaction.update(collectionAndDocument, "courses", current)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating Courses: ${exception.message}")
        }
    }

    fun deleteCourse(course: Course) {
        deleteElement("courses", course)
    }

    fun uploadExperience(experience: List<Experience>) {
        firestore.runTransaction { transaction ->
            val exist =
                transaction.get(collectionAndDocument).get("experiences") as? List<Map<String, Any>>
                    ?: emptyList()

            val current = exist.map {
                Experience(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    experienceTitle = (it["experienceTitle"] as? String) ?: "",
                    company = (it["company"] as? String) ?: "",
                    date = (it["date"] as? String) ?: "",
                    description = (it["description"] as? String) ?: "",
                )
            }.toMutableList()
            experience.forEach { new ->
                val existingIndex = current.indexOfFirst { it.id == new.id }
                if (existingIndex != -1) {
                    current[existingIndex] = new
                } else {
                    current.add(new)
                }
            }

            current.sortBy { it.id }
            transaction.update(collectionAndDocument, "experiences", current)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating Experience: ${exception.message}")
        }
    }

    fun deleteExperience(experience: Experience) {
        deleteElement("experiences", experience)
    }

    fun editAbout(about: String) {
        collectionAndDocument.update("about", about)
            .addOnFailureListener { exception ->
                throw Exception("Error Editing About: ${exception.message}")
            }
    }


    fun getAllMessages(): Flow<List<ContactMessage>> = callbackFlow {
        val listenerRegistration = collectionAndDocument.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val messageDocument =
                snapshot?.data?.get("contactMessage") as? List<Map<String, Any>> ?: emptyList()
            val currentMessages = messageDocument.map {
                ContactMessage(
                    date = (it["date"] as? String) ?: "",
                    email = (it["email"] as? String) ?: "",
                    message = (it["message"] as? String) ?: ""
                )
            }.reversed()

            trySend(currentMessages)
        }
        awaitClose { listenerRegistration.remove() }
    }

    fun deleteMessage(message: ContactMessage) {
        deleteElement("contactMessage", message)
    }

}