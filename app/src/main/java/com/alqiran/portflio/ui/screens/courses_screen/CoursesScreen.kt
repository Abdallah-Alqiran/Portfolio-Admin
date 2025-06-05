package com.alqiran.portflio.ui.screens.courses_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portflio.ui.screens.courses_screen.viewModel.CoursesViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alqiran.portflio.ui.components.loading_and_failed.FailedLoadingScreen
import com.alqiran.portflio.ui.components.loading_and_failed.LoadingProgressIndicator
import com.alqiran.portflio.ui.model.CourseUiModel
import com.alqiran.portflio.ui.screens.courses_screen.viewModel.CoursesState

@Composable
fun CoursesScreen() {
    val coursesViewModel = hiltViewModel<CoursesViewModel>()
    val coursesState by coursesViewModel.coursesState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        coursesViewModel.fetchAllCourses()
    }

    when (coursesState) {
        is CoursesState.Error -> {
            FailedLoadingScreen(
                onFailed = { coursesViewModel.fetchAllCourses() },
                errorMessage = (coursesState as CoursesState.Error).error
            )
        }

        CoursesState.Loading -> {
            LoadingProgressIndicator()
        }

        CoursesState.None -> Unit
        is CoursesState.Success -> {
            CoursesContentScreen((coursesState as CoursesState.Success).courses)
        }
    }
}

@Composable
fun CoursesContentScreen(courses: List<CourseUiModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        courses.forEach {course ->
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(bottom = 8.dp),
                ) {
                    Text(
                        text = course.courseName,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(8.dp),
                    )
                    Text(
                        text = course.courseDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier.padding(8.dp),
                    )
                }
            }
        }
    }
}