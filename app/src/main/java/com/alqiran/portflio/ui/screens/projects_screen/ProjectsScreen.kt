package com.alqiran.portflio.ui.screens.projects_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.alqiran.portflio.ui.navigation.NavigationAction
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alqiran.portflio.R
import com.alqiran.portflio.ui.components.loading_and_failed.FailedLoadingScreen
import com.alqiran.portflio.ui.components.loading_and_failed.LoadingProgressIndicator
import com.alqiran.portflio.ui.model.ProjectUiModel
import com.alqiran.portflio.ui.screens.home_screen.components.DefaultButton
import com.alqiran.portflio.ui.screens.projects_screen.viewModel.ProjectsState
import com.alqiran.portflio.ui.screens.projects_screen.viewModel.ProjectsViewModel
import com.alqiran.portflio.ui.utils.ButtonType


@Composable
fun ProjectsScreen(onNavigate: (NavigationAction) -> Unit) {
    val projectsViewModel = hiltViewModel<ProjectsViewModel>()
    val projectsState by projectsViewModel.projectState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        projectsViewModel.fetchAllProjects()
    }

    when (projectsState) {
        is ProjectsState.Error -> {
            FailedLoadingScreen(
                onFailed = { projectsViewModel.fetchAllProjects() },
                errorMessage = (projectsState as ProjectsState.Error).error
            )
        }

        ProjectsState.Loading -> {
            LoadingProgressIndicator()
        }

        is ProjectsState.Success -> {
            ProjectsContentScreen((projectsState as ProjectsState.Success).projects, onNavigate)
        }

        ProjectsState.None -> Unit
    }
}

@Composable
fun ProjectsContentScreen(projects: List<ProjectUiModel>, onNavigate: (NavigationAction) -> Unit) {

    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        projects.forEach { project ->
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(bottom = 8.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
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
                    Text(
                        text = project.projectName,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        DefaultButton(
                            "Details",
                            buttonType = ButtonType.ScreenNavigation(
                                navigationAction = NavigationAction.ToProject(project.id.toString()),
                                onNavigate = onNavigate
                            )
                        )
                    }
                }
            }
        }
    }
}