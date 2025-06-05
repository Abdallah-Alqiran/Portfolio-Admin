package com.alqiran.portflio.ui.screens.home_screen.viewModel

import com.alqiran.portflio.ui.model.UserUiModel

sealed class UserState {
    object None: UserState()
    object Loading: UserState()

    data class Success(val userData: UserUiModel): UserState()
    data class Error(val error: String): UserState()
}