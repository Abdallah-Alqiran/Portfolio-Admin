package com.alqiran.portfoliomainadmin.ui.screens.settings_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: FirebaseRepository
): ViewModel() {
    private val _settingsState = MutableStateFlow<SettingsState>(SettingsState.None)
    val settingsState = _settingsState.asStateFlow()

    val userId = userRepository.getUserId()

    fun logout() {
        _settingsState.value = SettingsState.Loading
        viewModelScope.launch {
            try {
                userRepository.logout()
                _settingsState.value = SettingsState.Success
            } catch (e: Exception) {
                _settingsState.value = SettingsState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}