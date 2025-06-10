package com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin

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
import com.alqiran.portfoliomainadmin.ui.components.HeadlineTextWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.AddItemTextButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DeleteItemTextButton
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.viewModel.TopTitleAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun TopTitleAdminScreen(
    user: String,
    image: String?,
    job: String,
    allAccounts: List<ContactAndAccountsUiModel>?,
    cv: String?
) {
    var userName by remember { mutableStateOf(user) }
    var imageUrl by remember { mutableStateOf(image) }
    var jobTitle by remember { mutableStateOf(job) }
    var cvUrl by remember { mutableStateOf(cv) }

    var accounts by remember { mutableStateOf(allAccounts) }


    val topTitleAdminViewModel: TopTitleAdminViewModel = hiltViewModel()
    val topTitleState by topTitleAdminViewModel.topTitleAdminState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (topTitleState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (topTitleState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            topTitleAdminViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            topTitleAdminViewModel.stateNone()
        }
        AdminState.None -> Unit
    }

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {

        item {
            CustomOutlinedTextFieldWidget(
                textValue = userName,
                textLabel = "Name",
                placeHolderLabel = "Enter your Name"
            ) {
                userName = it
            }
        }

        item {
            CustomOutlinedTextFieldWidget(
                textValue = jobTitle,
                textLabel = "Job",
                placeHolderLabel = "Enter your Job Title"
            ) {
                jobTitle = it
            }
        }

        item {
            CustomOutlinedTextFieldWidget(
                textValue = imageUrl ?: "",
                textLabel = "Image",
                placeHolderLabel = "Enter your Image URL"
            ) {
                imageUrl = it
            }
        }

        item {
            CustomOutlinedTextFieldWidget(
                textValue = cvUrl ?: "",
                textLabel = "CV",
                placeHolderLabel = "Enter your CV URL"
            ) {
                cvUrl = it
            }
        }

        item {
            DefaultButton(
                text = "Edit user Data",
                buttonType = ButtonType.UploadOnClick {
                    topTitleAdminViewModel.uploadUserData(userName, imageUrl, jobTitle, cvUrl)
                }
            )
        }

        item {
            HeadlineTextWidget("Accounts")
        }

        items(accounts ?: emptyList()) { account ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = account.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter account id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: account.id
                    accounts = accounts?.map { a ->
                        if (a == account) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = account.webName,
                    textLabel = "web",
                    placeHolderLabel = "website name",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    accounts = accounts?.map { a ->
                        if (a == account) a.copy(webName = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = account.url,
                textLabel = "account Link",
                placeHolderLabel = "Enter your account link"
            ) {
                accounts = accounts?.map { a ->
                    if (a == account) a.copy(url = it) else a
                }
            }
            DeleteItemTextButton {
                topTitleAdminViewModel.deleteAccount(account)
                accounts = accounts !!- account
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                accounts = (accounts ?: emptyList()) + ContactAndAccountsUiModel(
                    id = (accounts?.size ?: 0)
                )
            }
        }
        item {
            DefaultButton(
                text = "Edit Contact and Accounts",
                buttonType = ButtonType.UploadOnClick {
                    topTitleAdminViewModel.uploadContactAndAccounts(accounts)
                }
            )
        }
    }

}