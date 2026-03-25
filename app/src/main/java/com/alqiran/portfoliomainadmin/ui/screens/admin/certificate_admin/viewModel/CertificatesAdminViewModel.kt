package com.alqiran.portfoliomainadmin.ui.screens.admin.certificate_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toCertificate
import com.alqiran.portfoliomainadmin.data.mapper.toCertificates
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CertificatesAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadCertificates(allCertificates: List<CertificateUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadCertificates(allCertificates.toCertificates())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteCertificate(certificate: CertificateUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteCertificate(certificate.toCertificate())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun stateNone() {
        _state.value = AdminState.None
    }

}