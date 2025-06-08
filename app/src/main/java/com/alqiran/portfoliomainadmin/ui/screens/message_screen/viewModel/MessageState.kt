package com.alqiran.portfoliomainadmin.ui.screens.message_screen.viewModel

sealed class MessageState {
    object None: MessageState()
    object Loading: MessageState()
    object Success: MessageState()

    data class Error(val error: String): MessageState()
}