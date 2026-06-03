package com.alqiran.portfoliomainadmin.ui.screens.auth.register_screen.viewModel

sealed class RegisterState {
    data object Loading: RegisterState()
    data object None: RegisterState()
    data object Authenticated: RegisterState()
    data object UnAuthenticated: RegisterState()
    data class Error(val message: String): RegisterState()

    data class InvalidInput(var invalidInput: Map<String, String>): RegisterState()
}