package com.alqiran.portfoliomainadmin.ui.screens.home_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portfoliomainadmin.domain.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.mapper.toUserDataUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: FirebaseRepository
): ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.None)
    val userState = _userState.asStateFlow()

    fun fetchUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userState.value = UserState.Loading
            try {
                val userData = userRepository.getAllUserData().toUserDataUi()
                _userState.value = UserState.Success(userData)
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "Unknown Error")
            }
        }
    }

}