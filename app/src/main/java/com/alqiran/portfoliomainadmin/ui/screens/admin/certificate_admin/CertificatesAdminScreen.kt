package com.alqiran.portfoliomainadmin.ui.screens.admin.certificate_admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.ui.components.CustomOutlinedTextFieldWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.AddItemTextButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DeleteItemTextButton
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.certificate_admin.viewModel.CertificatesAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun CertificatesAdminScreen(allCertificates: List<CertificateUiModel>?) {

    val certificatesAdminVideModel: CertificatesAdminViewModel = hiltViewModel()
    val certificatesState by certificatesAdminVideModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (certificatesState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (certificatesState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            certificatesAdminVideModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            certificatesAdminVideModel.stateNone()
        }
        AdminState.None -> Unit
    }

    var certificates by remember { mutableStateOf(allCertificates) }

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(certificates?: emptyList()) { certificate ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = certificate.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter certificate id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: certificate.id
                    certificates = certificates?.map { a ->
                        if (a == certificate) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = certificate.certificateName,
                    textLabel = "certificate Name",
                    placeHolderLabel = "Enter certificate Name",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    certificates = certificates?.map { a ->
                        if (a == certificate) a.copy(certificateName = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = certificate.imageUrl,
                textLabel = "certificate Image",
                placeHolderLabel = "Enter your Image URL"
            ) {
                certificates = certificates?.map { a ->
                    if (a == certificate) a.copy(imageUrl = it) else a
                }
            }
            

            CustomOutlinedTextFieldWidget(
                textValue = certificate.description,
                textLabel = "certificate Description",
                placeHolderLabel = "Enter your certificate Description",
                isSingleLine = false,
                minLines = 4
            ) {
                certificates = certificates?.map { a ->
                    if (a == certificate) a.copy(description = it) else a
                }
            }


            DeleteItemTextButton {
                certificatesAdminVideModel.deleteCertificate(certificate)
                certificates = certificates !!- certificate
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                certificates = (certificates?: emptyList()) + CertificateUiModel(id = (certificates?.size?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit certificates",
                buttonType = ButtonType.UploadOnClick {
                    certificatesAdminVideModel.uploadCertificates(certificates?: emptyList())
                }
            )
        }
    }
}