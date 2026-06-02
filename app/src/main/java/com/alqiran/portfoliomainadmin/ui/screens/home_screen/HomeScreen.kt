package com.alqiran.portfoliomainadmin.ui.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.theme.PortfolioMainTheme
import com.alqiran.portfoliomainadmin.ui.components.SectionHeaderWithEdit
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultTextButton
import com.alqiran.portfoliomainadmin.ui.components.loading_and_failed.FailedLoadingScreen
import com.alqiran.portfoliomainadmin.ui.components.loading_and_failed.LoadingProgressIndicator
import com.alqiran.portfoliomainadmin.ui.helper.isValidUrl
import com.alqiran.portfoliomainadmin.ui.model.UserUiModel
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.AboutSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.CoursesSection
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.CertificatesSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.ContentsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.EducationSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.ExperienceSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.PendingRecommendationsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.ProjectsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.RecommendationsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.SkillsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.TechnologiesAndToolsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.TopTitleSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.components.VideoPresentationsSection
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.viewModel.UserState
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.viewModel.UserViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun HomeScreen(
    onNavigate: (NavigationAction) -> Unit,
    onStart: (List<ProjectUiModel>?, List<CourseUiModel>?) -> Unit
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
                onFailed = { userViewModel.fetchUserData() },
                errorMessage = (userData as UserState.Error).error
            )
        }
        UserState.Loading -> LoadingProgressIndicator()
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
            .padding(horizontal = 16.dp),
        state = listState,
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Header Section
        item {
            TopTitleSection(
                userData.userName,
                userData.userImage,
                userData.jobTitle,
                userData.contactAndAccounts,
                context
            )
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (userData.cvUrl.isValidUrl()) {
                    DefaultButton(
                        text = "Download CV",
                        buttonType = ButtonType.IntentNavigation(userData.cvUrl!!, context),
                    )
                }
                DefaultTextButton(
                    text = "Header",
                    onNavigate = onNavigate,
                    navigateAction = NavigationAction.ToTopTitleEdit(
                        userData.userName, userData.userImage, userData.jobTitle, 
                        userData.contactAndAccounts, userData.cvUrl
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // About Section
        item {
            SectionHeaderWithEdit("About", onNavigate, NavigationAction.ToAboutEdit(userData.about ?: ""))
            AboutSection(userData.about ?: "")
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Projects
        item {
            SectionHeaderWithEdit("Projects", onNavigate, NavigationAction.ToProjectsEdit(userData.projects ?: emptyList()), "All") {
                onNavigate(NavigationAction.ToViewAllProjects(userData.projects ?: emptyList()))
            }
            if (userData.projects != null) {
                ProjectsSection(userData.projects, onNavigate)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Experience Section
        item {
            SectionHeaderWithEdit("Experience", onNavigate, NavigationAction.ToExperienceEdit(userData.experiences ?: emptyList()))
            if (userData.experiences != null) {
                ExperienceSection(userData.experiences)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Skills
        item {
            SectionHeaderWithEdit("Skills", onNavigate, NavigationAction.ToSkillsEdit(userData.skills ?: emptyList()))
            if (userData.skills != null) {
                SkillsSection(userData.skills)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Technologies & Tools Section
        item {
            SectionHeaderWithEdit("Technologies", onNavigate, NavigationAction.ToTechnologiesAndToolsEdit(userData.technologiesAndTools ?: emptyList()))
            if (userData.technologiesAndTools != null) {
                TechnologiesAndToolsSection(userData.technologiesAndTools)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Courses
        item {
            SectionHeaderWithEdit("Courses", onNavigate, NavigationAction.ToCoursesEdit(userData.courses ?: emptyList()), "All") {
                onNavigate(NavigationAction.ToViewAllCourses(userData.courses ?: emptyList()))
            }
            if (userData.courses != null) {
                CoursesSection(userData.courses)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Certificates
        item {
            SectionHeaderWithEdit("Certificates", onNavigate, NavigationAction.ToCertificateEdit(userData.certificates ?: emptyList()))
            if (userData.certificates != null) {
                CertificatesSection(userData.certificates, onNavigate)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Contents
        item {
            SectionHeaderWithEdit("Resources", onNavigate, NavigationAction.ToContentEdit(userData.contentsTitle ?: emptyList()))
            if (userData.contentsTitle != null) {
                ContentsSection(userData.contentsTitle)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Video Presentations
        item {
            SectionHeaderWithEdit("Videos", onNavigate, NavigationAction.ToVideosEdit(userData.videos ?: emptyList()))
            if (userData.videos != null) {
                VideoPresentationsSection(userData.videos)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Recommendations
        item {
            SectionHeaderWithEdit(
                "Recommendations", 
                onNavigate, 
                NavigationAction.ToRecommendationsEdit(userData.recommendations ?: emptyList())
            )
            if (userData.recommendations != null) {
                RecommendationsSection(userData.recommendations)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Pending Recommendations
        item {
            SectionHeaderWithEdit(
                "Pending Recommendations", 
                onNavigate, 
                NavigationAction.ToPendingRecommendationsEdit(userData.pendingRecommendations ?: emptyList())
            )
            if (userData.pendingRecommendations != null) {
                PendingRecommendationsSection(userData.pendingRecommendations)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Education Section
        item {
            SectionHeaderWithEdit("Education", onNavigate, NavigationAction.ToEducationEdit(userData.education ?: emptyList()))
            if (userData.education != null) {
                EducationSection(userData.education)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    PortfolioMainTheme {
        HomeScreen(
            onNavigate = {},
            onStart = { _, _ -> }
        )
    }
}