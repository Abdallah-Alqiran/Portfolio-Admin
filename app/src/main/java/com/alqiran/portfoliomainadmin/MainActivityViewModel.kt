package com.alqiran.portfoliomainadmin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) : ViewModel() {

    init {
        initializeFCMToken()
    }

    private fun initializeFCMToken() {
        viewModelScope.launch {
            try {
                if (!firebaseRepository.isLoggedIn()) {
                    Log.d("Al-qiran", "User not logged in, skipping FCM token initialization")
                    return@launch
                }

                val savedToken = firebaseRepository.getCurrentFCMToken()
                
                if (savedToken.isNullOrEmpty()) {
                    Log.d("Al-qiran", "No saved FCM token found, generating new one")
                    generateAndSaveFCMToken()
                } else {
                    Log.d("Al-qiran", "Valid FCM token found, refreshing token")
                    generateAndSaveFCMToken()
                }
            } catch (e: Exception) {
                Log.e("Al-qiran", "Error initializing FCM token: ${e.message}", e)
                generateAndSaveFCMToken()
            }
        }
    }

    private fun generateAndSaveFCMToken() {
        try {
            firebaseRepository.updateFCMToken()
            Log.d("Al-qiran", "FCM token updated successfully")
        } catch (e: Exception) {
            Log.e("Al-qiran", "Error generating and saving FCM token: ${e.message}", e)
        }
    }

    fun askNotificationPermission(activity: ComponentActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d("Al-qiran", "Notification permission already granted")
                }

                activity.shouldShowRequestPermissionRationale(
                    Manifest.permission.POST_NOTIFICATIONS
                ) -> {
                    Log.d("Al-qiran", "Should show permission rationale")
                }

                else -> {
                    Log.d("Al-qiran", "Requesting notification permission")
                    activity.registerForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { isGranted ->
                        if (isGranted) {
                            Log.d("Al-qiran", "Notification permission granted")
                        } else {
                            Log.d("Al-qiran", "Notification permission denied")
                        }
                    }.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}


