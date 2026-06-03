package com.alqiran.portfoliomainadmin.ui.screens.auth.register_screen.viewModel

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
class RegisterViewModel @Inject constructor(
    private val userRepository: FirebaseRepository
): ViewModel() {

    private val _state = MutableStateFlow<RegisterState>(RegisterState.UnAuthenticated)
    val state = _state.asStateFlow()
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()


    private val _invalidElements = MutableStateFlow<Map<String, String>>(emptyMap())
    val invalidElements = _invalidElements.asStateFlow()

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }


    fun createUser() {
        val errors = registrationUtil( _email.value, _password.value)

        if (errors.isEmpty()) {
            _invalidElements.value = emptyMap()

            viewModelScope.launch {
                _state.value = RegisterState.Loading

                try {
                    userRepository.register(_email.value, _password.value, )

                    _state.value = RegisterState.Authenticated
                    Log.d("Al-qiran", "From block")

                } catch (e: Exception) {
                    _state.value = RegisterState.Error(e.message.toString())
                    if (e is java.net.UnknownHostException ||
                        e is java.net.SocketTimeoutException ||
                        e is java.io.IOException
                    ) {
                        _state.value =
                            RegisterState.Error("Network error. Please check your internet connection.")

                    } else {
                        _state.value = RegisterState.Error("Invalid email or password.")
                    }
                }
            }
        } else {
            _invalidElements.value = errors
            _state.value = RegisterState.InvalidInput(errors)
        }
    }

    private fun registrationUtil(
        email: String,
        password: String
    ): MutableMap<String, String> {
        val errors = mutableMapOf<String, String>()


        if (email.isEmpty()) {
            errors["email"] = "Email can't be empty"
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}\$".toRegex())) {
            errors["email"] = "Invalid email format"
        }

        if (password.isEmpty()) {
            errors["password"] = "Password can't be empty"
        } else if (password.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        return errors
    }


}