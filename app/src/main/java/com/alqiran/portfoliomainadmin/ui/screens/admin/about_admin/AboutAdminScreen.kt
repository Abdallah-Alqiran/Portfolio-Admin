package com.alqiran.portfoliomainadmin.ui.screens.admin.about_admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.about_admin.viewModel.AboutAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun AboutAdminScreen(allAbout: String) {

    var about by remember { mutableStateOf(allAbout) }

    val aboutViewModel: AboutAdminViewModel = hiltViewModel()
    val aboutState by aboutViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (aboutState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (aboutState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            aboutViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            aboutViewModel.stateNone()
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
                textValue = about,
                textLabel = "About",
                placeHolderLabel = "Enter your About",
                minLines = 5,
                isSingleLine = false
            ) {
                about = it
            }
        }
        item {
            DefaultButton(
                text = "Edit About",
                buttonType = ButtonType.UploadOnClick {
                    aboutViewModel.editAbout(about)
                }
            )
        }
    }
}