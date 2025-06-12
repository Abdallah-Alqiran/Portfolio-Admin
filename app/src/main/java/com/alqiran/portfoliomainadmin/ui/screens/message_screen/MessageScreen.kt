package com.alqiran.portfoliomainadmin.ui.screens.message_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.theme.PortfolioMainTheme
import com.alqiran.portfoliomainadmin.ui.components.loading_and_failed.LoadingProgressIndicator
import com.alqiran.portfoliomainadmin.ui.model.ContactMessageUiModel
import com.alqiran.portfoliomainadmin.ui.screens.message_screen.viewModel.MessageState
import com.alqiran.portfoliomainadmin.ui.screens.message_screen.viewModel.MessageViewModel

@Composable
fun MessageScreen() {

    val context = LocalContext.current

    val messageViewModel = hiltViewModel<MessageViewModel>()
    val messageState by messageViewModel.messageState.collectAsStateWithLifecycle()
    val messages by messageViewModel.messages.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        messageViewModel.fetchMessages()
    }

    when (messageState) {
        is MessageState.Error -> {
            Toast.makeText(
                context,
                (messageState as MessageState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
        }

        MessageState.Loading -> {
            LoadingProgressIndicator()
        }

        MessageState.Success -> {
            MessageContentScreen(messages, messageViewModel)
        }

        MessageState.None -> Unit
    }


}

@Composable
fun MessageContentScreen(
    messages: List<ContactMessageUiModel>,
    messageViewModel: MessageViewModel
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(messages) { message ->
            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = message.email,
                                color = MaterialTheme.colorScheme.tertiary,
                                style = MaterialTheme.typography.headlineSmall,
                            )
                            Text(
                                "Date: ${message.date.substring(0, 16)}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                        IconButton(onClick = {
                            messageViewModel.deleteMessage(message)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = "Delete Food"
                            )
                        }
                    }
                    Text(
                        message.message,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium,
                    )

                }
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    PortfolioMainTheme {
        MessageScreen()
    }
}