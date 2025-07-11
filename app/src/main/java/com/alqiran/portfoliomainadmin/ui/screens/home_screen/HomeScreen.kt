package com.alqiran.portfoliomainadmin.ui.screens.home_screen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.theme.PortfolioMainTheme
import com.alqiran.portfoliomainadmin.ui.components.HeadlineTextWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultTextButton
import com.alqiran.portfoliomainadmin.ui.components.loading_and_failed.FailedLoadingScreen
import com.alqiran.portfoliomainadmin.ui.components.loading_and_failed.LoadingProgressIndicator
import com.alqiran.portfoliomainadmin.ui.helper.isValidUrl
import com.alqiran.portfoliomainadmin.ui.model.UserUiModel
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.AboutSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.Courses
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.EducationSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.ExperienceSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.ProjectsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.SkillsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.TechnologiesAndToolsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.TopTitleSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.viewModel.UserState
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.viewModel.UserViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType


@Composable
fun HomeScreen(
    onNavigate: (NavigationAction) -> Unit,
    onStart:(List<ProjectUiModel>?, List<CourseUiModel>?) -> Unit
) {

    val userViewModel: UserViewModel = hiltViewModel()
    val userData by userViewModel.userState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        userViewModel.fetchUserData()
    }

    when (userData) {
        is UserState.Success -> {
            val projectsAndCourses = (userData as UserState.Success).userData
            onStart(projectsAndCourses.projects, projectsAndCourses.courses)
            HomeContentScreen(projectsAndCourses, onNavigate)
        }

        is UserState.Error -> {
            FailedLoadingScreen(
                onFailed = {
                    Log.d("Al-qiran", "From Home Screen")
                    userViewModel.fetchUserData()
                },
                errorMessage = (userData as UserState.Error).error
            )
        }

        UserState.Loading -> {
            LoadingProgressIndicator()
        }
        UserState.None -> Unit
    }


}

@Composable
fun HomeContentScreen(userData: UserUiModel, onNavigate: (NavigationAction) -> Unit) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        item {
            TopTitleSection(
                userData.userName,
                userData.userImage,
                userData.jobTitle,
                userData.contactAndAccounts,
                context,
            )
        }

        item {
            if (userData.cvUrl.isValidUrl()) {
                DefaultButton(
                    text = "Download CV",
                    buttonType = ButtonType.IntentNavigation(userData.cvUrl!!, context)
                )
            }
        }

        item {
            DefaultTextButton(
                text = "Top Title",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToTopTitleEdit(userData.userName, userData.userImage, userData.jobTitle, userData.contactAndAccounts, userData.cvUrl),
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            if (userData.education != null) {
                HeadlineTextWidget(text = "Education")
                EducationSection(userData.education)
            }
        }

        item {
            DefaultTextButton(
                text = "Education",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToEducationEdit(educations = userData.education?: emptyList()),
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            if (userData.about != null) {
                HeadlineTextWidget(text = "About")
                AboutSection(userData.about)
            }
        }

        item {
            DefaultTextButton(
                text = "About",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToAboutEdit(userData.about?: ""),
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            if (userData.technologiesAndTools != null) {
                HeadlineTextWidget(text = "Technologies and Tools")
                TechnologiesAndToolsSection(userData.technologiesAndTools)
            }
        }

        item {
            DefaultTextButton(
                text = "Technologies",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToTechnologiesAndToolsEdit(userData.technologiesAndTools?: emptyList()),
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            if (userData.skills != null) {
                HeadlineTextWidget(text = "Skills")
                SkillsSection(userData.skills)
            }
        }

        item {
            DefaultTextButton(
                text = "Skills",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToSkillsEdit(userData.skills?: emptyList()),
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            if (userData.projects != null) {
                HeadlineTextWidget(text = "Projects")
                ProjectsSection(userData.projects, onNavigate)
                DefaultTextButton(
                    "Projects",
                    onNavigate = onNavigate,
                    navigateAction = NavigationAction.ToViewAllProjects(userData.projects)
                )
            }
        }

        item {
            DefaultTextButton(
                text = "Projects",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToProjectsEdit(userData.projects?: emptyList()),
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            if (userData.courses != null) {
                HeadlineTextWidget(text = "Courses")
                Courses(userData.courses)
                DefaultTextButton(
                    "Courses",
                    onNavigate = onNavigate,
                    navigateAction = NavigationAction.ToViewAllCourses(userData.courses)
                )
            }
        }

        item {
            DefaultTextButton(
                text = "Courses",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToCoursesEdit(userData.courses?: emptyList()),
                color = MaterialTheme.colorScheme.error
            )
        }

        item {
            if (userData.experiences != null) {
                HeadlineTextWidget(text = "Experience")
                ExperienceSection(userData.experiences)
            }
        }

        item {
            Log.d("Al-qiran Ex", "From Home Screen: ${userData.experiences}")

            DefaultTextButton(
                text = "Experience",
                onNavigate = onNavigate,
                navigateAction = NavigationAction.ToExperienceEdit(userData.experiences?: emptyList()),
                color = MaterialTheme.colorScheme.error
            )
        }

    }
}


@Preview
@Composable
private fun Prev() {
    PortfolioMainTheme {
        HomeScreen(
            onNavigate = {},
            onStart = {_,_->}
        )
    }
}