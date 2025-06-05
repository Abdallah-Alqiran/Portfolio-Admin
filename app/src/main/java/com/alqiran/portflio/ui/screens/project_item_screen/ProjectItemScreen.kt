package com.alqiran.portflio.ui.screens.project_item_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portflio.ui.screens.project_item_screen.viewModel.ProjectItemViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alqiran.portflio.R
import com.alqiran.portflio.ui.components.loading_and_failed.FailedLoadingScreen
import com.alqiran.portflio.ui.components.HeadlineTextWidget
import com.alqiran.portflio.ui.components.loading_and_failed.LoadingProgressIndicator
import com.alqiran.portflio.ui.model.ProjectUiModel
import com.alqiran.portflio.ui.screens.home_screen.components.DefaultButton
import com.alqiran.portflio.ui.screens.project_item_screen.viewModel.ProjectItemState
import com.alqiran.portflio.ui.utils.ButtonType


@Composable
fun ProjectItemScreen(projectId: Int) {

    val projectItemViewModel = hiltViewModel<ProjectItemViewModel>()
    val projectState by projectItemViewModel.projectState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        projectItemViewModel.fetchProjectItem(projectId)
    }

    when (projectState) {
        is ProjectItemState.Error -> {
            FailedLoadingScreen(
                onFailed = { projectItemViewModel.fetchProjectItem(projectId) },
                errorMessage = (projectState as ProjectItemState.Error).error
            )
        }

        ProjectItemState.Loading -> {
            LoadingProgressIndicator()
        }

        is ProjectItemState.Success -> {
            ProjectItemContentScreen((projectState as ProjectItemState.Success).project)
        }

        ProjectItemState.None -> {}
    }
}

@Composable
fun ProjectItemContentScreen(project: ProjectUiModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(project.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Project Image",
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.ic_loading_project),
                error = painterResource(id = R.drawable.ic_failed),
                contentScale = ContentScale.FillBounds,
            )
        }

        Box(Modifier.padding(8.dp))

        Text(
            text = project.projectName,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )

        HeadlineTextWidget("Description")

        Text(
            project.description,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Box(Modifier.padding(8.dp))

        DefaultButton(
            text = "View on GitHub",
            buttonType = ButtonType.IntentNavigation(url = project.url, context = context)
        )

        Box(Modifier.padding(8.dp))

    }
}