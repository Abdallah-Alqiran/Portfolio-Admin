package com.alqiran.portfoliomainadmin.ui.screens.admin

sealed class AdminState {
    object None: AdminState()
    object Loading: AdminState()

    object Success: AdminState()

    data class Error(val error: String): AdminState()
}