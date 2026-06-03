package com.alqiran.portfoliomainadmin.ui.screens.splash.viewModel

sealed class SplashState {
    object Loading: SplashState()
    data class Success(val isLoggedIn: Boolean): SplashState()
    data class Error(val error: String): SplashState()
}