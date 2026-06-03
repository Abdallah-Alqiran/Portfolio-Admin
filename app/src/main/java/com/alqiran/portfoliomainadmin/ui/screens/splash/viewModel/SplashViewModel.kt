package com.alqiran.portfoliomainadmin.ui.screens.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: FirebaseRepository
): ViewModel() {
    private val _splashState = MutableStateFlow<SplashState>(SplashState.Loading)
    val splashState = _splashState.asStateFlow()
    fun checkUserLoggedIn() {
        _splashState.value = SplashState.Loading
        viewModelScope.launch {
            try {
                val isLoggedIn = userRepository.isLoggedIn()
                _splashState.value = SplashState.Success(isLoggedIn)
            } catch (e: Exception) {
                _splashState.value = SplashState.Error(e.message ?: "Unknown Error")
            }
        }

    }
}