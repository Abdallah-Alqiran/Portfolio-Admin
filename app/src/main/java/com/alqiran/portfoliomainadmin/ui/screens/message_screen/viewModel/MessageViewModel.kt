package com.alqiran.portfoliomainadmin.ui.screens.message_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portfoliomainadmin.data.mapper.toContactMessage
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.mapper.toContactMessageDataUi
import com.alqiran.portfoliomainadmin.ui.model.ContactMessageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(private val messageRepo: FirebaseRepository) :
    ViewModel() {

    private val _messageState = MutableStateFlow<MessageState>(MessageState.None)
    val messageState = _messageState.asStateFlow()
    private val _messages = MutableStateFlow<List<ContactMessageUiModel>>(emptyList())
    val messages = _messages.asStateFlow()

    fun fetchMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            _messageState.value = MessageState.Loading
            try {
                messageRepo.getAllMessages().collect { messageList ->
                    _messages.value = messageList.toContactMessageDataUi()
                    _messageState.value = MessageState.Success
                }
            } catch (e: Exception) {
                _messageState.value = MessageState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun deleteMessage(message: ContactMessageUiModel) {
        messageRepo.deleteMessage(message.toContactMessage())
    }
}