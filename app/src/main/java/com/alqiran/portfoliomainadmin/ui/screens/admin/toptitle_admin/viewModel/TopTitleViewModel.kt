package com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toContactAndAccounts
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TopTitleViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
): ViewModel() {

    private val _topTitleState = MutableStateFlow<TopTitleState>(TopTitleState.None)
    val topTitleState = _topTitleState.asStateFlow()

    fun uploadUserData(
        user: String,
        image: String?,
        job: String,
        cv: String?
    ) {
        _topTitleState.value = TopTitleState.Loading
        try {
            uploadRepo.uploadUserData(user, image, job, cv)
            _topTitleState.value = TopTitleState.Success
        } catch (e: Exception) {
            _topTitleState.value = TopTitleState.Error(e.message.toString())
        }
    }

    fun uploadContactAndAccounts(
        accounts: List<ContactAndAccountsUiModel>?,
    ) {
        _topTitleState.value = TopTitleState.Loading
        if (accounts == null) {
            _topTitleState.value = TopTitleState.Error("There are null values")
        } else {
            try {
                uploadRepo.uploadContactAndAccounts(accounts.toContactAndAccounts())
            } catch (e: Exception) {
                _topTitleState.value = TopTitleState.Error(e.message.toString())
            }
        }
    }

    fun stateNone() {
        _topTitleState.value = TopTitleState.None
    }
}