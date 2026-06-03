package com.alqiran.portfoliomainadmin.ui.screens.auth.login_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: FirebaseRepository
): ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.UnAuthenticated)
    val state = _state.asStateFlow()
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _invalidElements = MutableStateFlow<Map<String, String>>(emptyMap())
    val invalidElements = _invalidElements.asStateFlow()

    fun logInUser() {
        val errors = mutableMapOf<String, String>()

        if (!_email.value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}\$".toRegex())) {
            errors["email"] = "Invalid email format"
        }

        if (_password.value.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        if (errors.isEmpty()) {
            _invalidElements.value = errors
            _state.value = LoginState.InvalidInput(errors)
            signInUser(_email.value, _password.value)
        } else {
            _invalidElements.value = errors
            _state.value = LoginState.InvalidInput(errors)
        }
    }

    private fun signInUser(
        email: String,
        password: String,
    ) {
        _state.value = LoginState.Loading
        viewModelScope.launch {
            try {
                userRepository.login(email, password)
                _state.value = LoginState.Authenticated
            } catch (e: Exception) {
                _state.value = LoginState.Error(e.message ?: "Unknown Error")
                Log.d("Al-qiran", "Error: ${e.message}")
            }
        }
    }


    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }
}