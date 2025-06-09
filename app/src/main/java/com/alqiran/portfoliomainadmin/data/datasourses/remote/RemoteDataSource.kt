package com.alqiran.portfoliomainadmin.data.datasourses.remote

import android.util.Log
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactAndAccounts
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
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

    fun uploadContactAndAccounts(accounts: List<ContactAndAccounts>) {

        firestore.runTransaction {  transaction ->
            val existingAccounts = transaction.get(collectionAndDocument).get("contactsAndAccounts") as? List<Map<String, Any>> ?: emptyList()

            val currentAccounts = existingAccounts.map {
                ContactAndAccounts(
                    id = (it["id"] as? Long)?.toInt() ?: 0,
                    webName = it["webName"] as? String ?: "",
                    url = it["url"] as? String ?: ""
                )
            }.toMutableList()

            accounts.forEach { newAccount ->
                val existingIndex = currentAccounts.indexOfFirst { it.id == newAccount.id }
                if (existingIndex != -1) {
                    currentAccounts[existingIndex] = newAccount
                } else {
                    currentAccounts.add(newAccount)
                }
            }

            currentAccounts.sortBy { it.id }
            transaction.update(collectionAndDocument, "contactAndAccounts", currentAccounts)
            null
        }.addOnFailureListener { exception ->
            throw Exception("Error updating contacts: ${exception.message}")
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
}
