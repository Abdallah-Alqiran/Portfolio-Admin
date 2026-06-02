package com.alqiran.portfoliomainadmin.ui.screens.admin.recommendations_admin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portfoliomainadmin.data.mapper.toPendingRecommendation
import com.alqiran.portfoliomainadmin.data.mapper.toRecommendation
import com.alqiran.portfoliomainadmin.data.mapper.toRecommendations
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.PendingRecommendationUiModel
import com.alqiran.portfoliomainadmin.ui.model.RecommendationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationAdminViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun acceptRecommendation(pending: PendingRecommendationUiModel) {
        _state.value = AdminState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.acceptRecommendation(pending.toPendingRecommendation())
                _state.value = AdminState.Success
            } catch (e: Exception) {
                _state.value = AdminState.Error(e.message ?: "Error accepting recommendation")
            }
        }
    }

    fun rejectRecommendation(pending: PendingRecommendationUiModel) {
        _state.value = AdminState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.rejectRecommendation(pending.toPendingRecommendation())
                _state.value = AdminState.Success
            } catch (e: Exception) {
                _state.value = AdminState.Error(e.message ?: "Error rejecting recommendation")
            }
        }
    }

    fun updateRecommendationsOrder(recommendations: List<RecommendationUiModel>) {
        _state.value = AdminState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.uploadRecommendations(recommendations.toRecommendations())
                _state.value = AdminState.Success
            } catch (e: Exception) {
                _state.value = AdminState.Error(e.message ?: "Error updating order")
            }
        }
    }

    fun deleteRecommendation(recommendation: RecommendationUiModel) {
        _state.value = AdminState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteRecommendation(recommendation.toRecommendation())
                _state.value = AdminState.Success
            } catch (e: Exception) {
                _state.value = AdminState.Error(e.message ?: "Error deleting recommendation")
            }
        }
    }

    fun resetState() {
        _state.value = AdminState.None
    }
}
