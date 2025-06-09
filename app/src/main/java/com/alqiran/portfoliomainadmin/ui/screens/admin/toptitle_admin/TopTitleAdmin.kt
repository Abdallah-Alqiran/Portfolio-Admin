package com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.viewModel.TopTitleState
import com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.viewModel.TopTitleViewModel
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


    val topTitleAdminViewModel: TopTitleViewModel = hiltViewModel()
    val topTitleState by topTitleAdminViewModel.topTitleState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when(topTitleState) {
        is TopTitleState.Error -> {
            Toast.makeText(context, (topTitleState as TopTitleState.Error).error, Toast.LENGTH_SHORT).show()
            topTitleAdminViewModel.stateNone()
        }
        TopTitleState.Loading -> {
        }
        TopTitleState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            topTitleAdminViewModel.stateNone()
        }
        TopTitleState.None -> Unit
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

            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {
                    accounts = (accounts ?: emptyList()) + ContactAndAccountsUiModel(
                        id = (accounts?.size ?: 0)
                    )
                }) {
                    Text(
                        "Add Item",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
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