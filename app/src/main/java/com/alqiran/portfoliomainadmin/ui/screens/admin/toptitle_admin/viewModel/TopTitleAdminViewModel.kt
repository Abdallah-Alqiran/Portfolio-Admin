package com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toContactAndAccount
import com.alqiran.portfoliomainadmin.data.mapper.toContactAndAccounts
import com.alqiran.portfoliomainadmin.data.mapper.toEducation
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TopTitleAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
): ViewModel() {

    private val _topTitleAdminState = MutableStateFlow<AdminState>(AdminState.None)
    val topTitleAdminState = _topTitleAdminState.asStateFlow()

    fun uploadUserData(
        user: String,
        image: String?,
        job: String,
        cv: String?
    ) {
        _topTitleAdminState.value = AdminState.Loading
        try {
            uploadRepo.uploadUserData(user, image, job, cv)
            _topTitleAdminState.value = AdminState.Success
        } catch (e: Exception) {
            _topTitleAdminState.value = AdminState.Error(e.message.toString())
        }
    }

    fun uploadContactAndAccounts(
        accounts: List<ContactAndAccountsUiModel>?,
    ) {
        _topTitleAdminState.value = AdminState.Loading
        if (accounts == null) {
            _topTitleAdminState.value = AdminState.Error("There are null values")
        } else {
            try {
                uploadRepo.uploadContactAndAccounts(accounts.toContactAndAccounts())
            } catch (e: Exception) {
                _topTitleAdminState.value = AdminState.Error(e.message.toString())
            }
        }
    }

    fun deleteAccount(accountsUiModel: ContactAndAccountsUiModel) {
        _topTitleAdminState.value = AdminState.Loading
        try {
            uploadRepo.deleteContactAndAccount(accountsUiModel.toContactAndAccount())
            _topTitleAdminState.value = AdminState.Success
        } catch (e: Exception) {
            _topTitleAdminState.value = AdminState.Error(e.message.toString())
        }
    }

    fun stateNone() {
        _topTitleAdminState.value = AdminState.None
    }
}