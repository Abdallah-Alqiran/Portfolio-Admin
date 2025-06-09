package com.alqiran.portfoliomainadmin.ui.screens.message_screen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.mapper.toContactMessage
import com.alqiran.portfoliomainadmin.ui.model.ContactMessageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(private val messageRepo: FirebaseRepository): ViewModel() {

    private val _messageState = MutableStateFlow<MessageState>(MessageState.None)
    val messageState = _messageState.asStateFlow()

    fun sendMessage(contactMessageUiModel: ContactMessageUiModel?) {

        _messageState.value = MessageState.None

        if (contactMessageUiModel == null) {
            Log.d("Al-qiran", "Contact is null")
            return
        }

        if (!contactMessageUiModel.email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}\$".toRegex())) {
            _messageState.value = MessageState.Error("Please Enter a Valid Email")
            return
        }

        if (contactMessageUiModel.message.isEmpty()) {
            _messageState.value = MessageState.Error("Please enter a message")
            return
        }

        _messageState.value = MessageState.Loading
        try {
            messageRepo.sendMessage(contactMessageUiModel.toContactMessage())
            _messageState.value = MessageState.Success
        } catch (e: Exception) {
            _messageState.value = MessageState.Error(e.message.toString())
        }
    }

}