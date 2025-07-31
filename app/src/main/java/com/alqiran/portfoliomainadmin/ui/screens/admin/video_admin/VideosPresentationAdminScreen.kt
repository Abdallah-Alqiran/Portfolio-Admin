package com.alqiran.portfoliomainadmin.ui.screens.admin.video_admin

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
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.video_admin.viewModel.VideosAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun VideoPresentationsAdminScreen(allVideos: List<VideoPresentationUiModel>?) {

    val videosAdminViewModel: VideosAdminViewModel = hiltViewModel()
    val videosState by videosAdminViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (videosState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (videosState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            videosAdminViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            videosAdminViewModel.stateNone()
        }
        AdminState.None -> Unit
    }

    var videos by remember { mutableStateOf(allVideos) }

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(videos?: emptyList()) { video ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = video.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter video id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: video.id
                    videos = videos?.map { a ->
                        if (a == video) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = video.videoTitle,
                    textLabel = "video Name",
                    placeHolderLabel = "Enter video Name",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    videos = videos?.map { a ->
                        if (a == video) a.copy(videoTitle = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = video.videoUrl,
                textLabel = "video Image",
                placeHolderLabel = "Enter your Image URL"
            ) {
                videos = videos?.map { a ->
                    if (a == video) a.copy(videoUrl = it) else a
                }
            }


            DeleteItemTextButton {
                videosAdminViewModel.deleteVideo(video)
                videos = videos !!- video
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                videos = (videos?: emptyList()) + VideoPresentationUiModel(id = (videos?.size?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit videos",
                buttonType = ButtonType.UploadOnClick {
                    videosAdminViewModel.uploadVideos(videos?: emptyList())
                }
            )
        }
    }
}