package com.alqiran.portfoliomainadmin.data.datasourses.remote

import android.util.Log
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.ContactMessage
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.User
import com.alqiran.portfoliomainadmin.utils.Constants.Companion.COLLECTION_NAME
import com.alqiran.portfoliomainadmin.utils.Constants.Companion.DOCUMENT_USER_NAME
import com.alqiran.portfoliomainadmin.utils.isOnline
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getAllUserData(): User {

        if (!isOnline()) {
            try {
                val localSnapshot = firestore.collection(COLLECTION_NAME)
                    .document(DOCUMENT_USER_NAME)
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
            val serverSnapshot = firestore.collection(COLLECTION_NAME)
                .document(DOCUMENT_USER_NAME)
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
            val message = firestore.collection(COLLECTION_NAME).document(DOCUMENT_USER_NAME)
            message.update("contactMessage", FieldValue.arrayUnion(contactMessage))
                .addOnFailureListener {
                    throw Exception("There is Error sending message")
                }
        } catch (e: Exception) {
            throw e
        }
    }

}
