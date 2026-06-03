package com.alqiran.portfoliomainadmin.ui.screens.settings_screen.viewModel

sealed class SettingsState {
    object None: SettingsState()
    object Loading: SettingsState()
    data object Success: SettingsState()
    data class Error(val error: String): SettingsState()
}