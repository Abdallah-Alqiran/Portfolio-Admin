package com.alqiran.portfoliomainadmin.ui.screens.admin.recommendations_admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.PendingRecommendationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.recommendations_admin.viewModel.RecommendationAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun PendingRecommendationsAdminScreen(pendingRecommendations: List<PendingRecommendationUiModel>?) {
    val viewModel: RecommendationAdminViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var pendingList by remember { mutableStateOf(pendingRecommendations) }

    LaunchedEffect(state) {
        when (state) {
            is AdminState.Error -> {
                Toast.makeText(context, (state as AdminState.Error).error, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            AdminState.Success -> {
                Toast.makeText(context, "Action Successful", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> Unit
        }
    }

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(16.dp),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (pendingList.isNullOrEmpty()) {
            item {
                Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text(text = "No pending recommendations", style = MaterialTheme.typography.bodyLarge)
                }
            }
        } else {
            items(pendingList ?: emptyList(), key = { it.id }) { pending ->
                PendingRecommendationItem(
                    pending = pending,
                    onAccept = {
                        viewModel.acceptRecommendation(pending)
                        pendingList = pendingList?.filter { it.id != pending.id }
                    },
                    onReject = {
                        viewModel.rejectRecommendation(pending)
                        pendingList = pendingList?.filter { it.id != pending.id }
                    }
                )
            }
        }
    }
}

@Composable
fun PendingRecommendationItem(
    pending: PendingRecommendationUiModel,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = pending.userName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = pending.email,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pending.message,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = pending.date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    DefaultButton(
                        text = "Accept",
                        buttonType = ButtonType.UploadOnClick(onAccept)
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    DefaultButton(
                        text = "Reject",
                        buttonType = ButtonType.UploadOnClick(onReject)
                    )
                }
            }
        }
    }
}
