package com.alqiran.portfoliomainadmin.ui.screens.admin.recommendations_admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.ui.components.CustomIdDropdownWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.DeleteItemTextButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.RecommendationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.recommendations_admin.viewModel.RecommendationAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun RecommendationsAdminScreen(allRecommendations: List<RecommendationUiModel>?) {
    val viewModel: RecommendationAdminViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var recommendations by remember { mutableStateOf(allRecommendations) }

    LaunchedEffect(state) {
        when (state) {
            is AdminState.Error -> {
                Toast.makeText(context, (state as AdminState.Error).error, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            AdminState.Success -> {
                Toast.makeText(context, "Changes Saved Successfully", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> Unit
        }
    }

    val displayList = remember(recommendations) {
        recommendations?.sortedWith(
            compareBy<RecommendationUiModel> { it.order ?: Int.MAX_VALUE }
                .thenByDescending { it.date }
        ) ?: emptyList()
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
        items(displayList, key = { it.id }) { recommendation ->
            RecommendationAdminItem(
                recommendation = recommendation,
                allOtherOrders = recommendations?.filter { it.id != recommendation.id }?.mapNotNull { it.order } ?: emptyList(),
                onOrderSwap = { newOrder ->
                    val other = recommendations?.find { it.order == newOrder }
                    recommendations = recommendations?.map { item ->
                        when {
                            item.id == recommendation.id -> item.copy(order = newOrder)
                            other != null && item.id == other.id -> item.copy(order = recommendation.order)
                            else -> item
                        }
                    }
                },
                onDelete = {
                    viewModel.deleteRecommendation(recommendation)
                    recommendations = recommendations?.filter { it.id != recommendation.id }
                }
            )
            HorizontalDivider(modifier = Modifier.padding(top = 8.dp), color = MaterialTheme.colorScheme.outlineVariant)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            DefaultButton(
                text = "Save Changes",
                buttonType = ButtonType.UploadOnClick {
                    viewModel.updateRecommendationsOrder(recommendations ?: emptyList())
                }
            )
        }
    }
}

@Composable
fun RecommendationAdminItem(
    recommendation: RecommendationUiModel,
    allOtherOrders: List<Int>,
    onOrderSwap: (Int) -> Unit,
    onDelete: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            CustomIdDropdownWidget(
                currentId = recommendation.order ?: 0,
                allOtherIds = allOtherOrders,
                onSwap = onOrderSwap,
                modifier = Modifier.width(80.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recommendation.userName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = recommendation.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            DeleteItemTextButton(onClick = onDelete)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = recommendation.message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
