package com.alqiran.portfoliomainadmin.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alqiran.portfoliomainadmin.ui.components.bars.BottomBar
import com.alqiran.portfoliomainadmin.ui.components.bars.TopBar
import com.alqiran.portfoliomainadmin.ui.model.CertificateUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.about_admin.AboutAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.certificate_admin.CertificatesAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.content_admin.ContentsAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.courses_admin.CoursesAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.education_admin.EducationAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.experience_admin.ExperienceAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.projects_admin.ProjectAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.skills_admin.SkillsAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.technologies_admin.TechnologiesAndToolsAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.TopTitleAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.admin.video_admin.VideoPresentationsAdminScreen
import com.alqiran.portfoliomainadmin.ui.screens.courses_screen.CoursesScreen
import com.alqiran.portfoliomainadmin.ui.screens.home_screen.HomeScreen
import com.alqiran.portfoliomainadmin.ui.screens.message_screen.MessageScreen
import com.alqiran.portfoliomainadmin.ui.screens.project_item_screen.ProjectItemScreen
import com.alqiran.portfoliomainadmin.ui.screens.projects_screen.ProjectsScreen
import com.alqiran.portfoliomainadmin.ui.screens.splash.SplashScreen
import kotlin.reflect.typeOf


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    val topBar = remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(-1) }

    var currentProjects: List<ProjectUiModel> = emptyList()
    var currentCourses: List<CourseUiModel> = emptyList()


    val onNavigate: (NavigationAction) -> Unit = { action ->

        when (action) {
            is NavigationAction.ToProject -> {
                navController.navigate(ProjectItemRoute(project = action.project))
            }

            is NavigationAction.ToViewAllCourses -> {
                navController.navigate(CoursesScreenRoute(courses = action.courses))
            }

            is NavigationAction.ToViewAllProjects -> {
                navController.navigate(ProjectsScreenRoute(projects = action.projects))
            }

            is NavigationAction.ToTopTitleEdit -> {
                navController.navigate(
                    TopTitleAdminScreenRoute(
                        userName = action.userName,
                        userImage = action.userImage,
                        jobTitle = action.jobTitle,
                        accounts = action.accounts,
                        cvUrl = action.cvUrl
                    )
                )
            }

            is NavigationAction.ToAboutEdit -> {
                navController.navigate(AboutAdminScreenRoute(action.about))
            }

            is NavigationAction.ToCoursesEdit -> {
                navController.navigate(CoursesAdminScreenRoute(action.courses))
            }

            is NavigationAction.ToEducationEdit -> {
                navController.navigate(EducationAdminScreenRoute(action.educations))
            }

            is NavigationAction.ToExperienceEdit -> {
                navController.navigate(ExperienceAdminScreenRoute(action.experience))
            }

            is NavigationAction.ToProjectsEdit -> {
                navController.navigate(ProjectsAdminScreenRoute(action.projects))
            }

            is NavigationAction.ToSkillsEdit -> {
                navController.navigate(SkillsAdminScreenRoute(action.skills))
            }

            is NavigationAction.ToTechnologiesAndToolsEdit -> {
                navController.navigate(TechnologiesAndToolsAdminScreenRoute(action.technologiesAndTools))
            }

            is NavigationAction.ToContentEdit -> {
                navController.navigate(ContentAdminScreenRoute(action.contentsTitle))
            }

            is NavigationAction.ToCertificateEdit -> {
                navController.navigate(CertificateAdminScreenRoute(action.certificates))
            }

            is NavigationAction.ToVideosEdit -> {
                navController.navigate(VideoAdminScreenRoute(action.videos))
            }

            NavigationAction.Nothing -> {}
        }
    }


    Scaffold(
        topBar = {
            when (topBar.value) {
                "Home" -> {
                    TopBar("Home", onClick = {})
                }

                "Project" -> {
                    TopBar("Project", onClick = { navController.popBackStack() })
                }

                "Projects" -> {
                    TopBar("Projects", onClick = { navController.popBackStack() })
                }

                "Courses" -> {
                    TopBar("Courses", onClick = { navController.popBackStack() })
                }

                "Message" -> {
                    TopBar("Contact Me", onClick = { navController.popBackStack() })
                }

                "EditTopTitle" -> {
                    TopBar("Edit TopTitle", onClick = { navController.popBackStack() })
                }

                "EditAbout" -> {
                    TopBar("Edit TopTitle", onClick = { navController.popBackStack() })
                }

                "EditCourses" -> {
                    TopBar("Edit Courses", onClick = { navController.popBackStack() })
                }

                "EditEducation" -> {
                    TopBar("Edit Education", onClick = { navController.popBackStack() })
                }

                "EditTechnologies" -> {
                    TopBar("Edit Technology", onClick = { navController.popBackStack() })
                }

                "EditSkills" -> {
                    TopBar("Edit Skills", onClick = { navController.popBackStack() })
                }

                "EditProjects" -> {
                    TopBar("Edit Projects", onClick = { navController.popBackStack() })
                }

                "EditExperience" -> {
                    TopBar("Edit Experience", onClick = { navController.popBackStack() })
                }

                "EditContents" -> {
                    TopBar("Edit Contents", onClick = { navController.popBackStack() })
                }

                "EditCertificates" -> {
                    TopBar("Edit Certificates", onClick = { navController.popBackStack() })
                }

                "EditVideos" -> {
                    TopBar("Edit Video Presentation", onClick = { navController.popBackStack() })
                }
            }
        },
        bottomBar = {
            if (selectedIndex != -1) {
                BottomBar(
                    selectedIndex = selectedIndex,
                    onItemSelected = {
                        selectedIndex = it
                        when (selectedIndex) {
                            0 -> {
                                navController.navigate(HomeScreenRoute) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }

                            1 -> {
                                navController.navigate(ProjectsScreenRoute(projects = currentProjects))
                            }

                            2 -> navController.navigate(CoursesScreenRoute(courses = currentCourses))
                            3 -> navController.navigate(MessageScreenRoute)
                        }
                    },
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = SplashScreenRoute,
            modifier = Modifier.padding(paddingValues)
        ) {

            // Splash
            composable<SplashScreenRoute> {
                selectedIndex = -1
                topBar.value = "Splash"
                SplashScreen {
                    navController.navigate(HomeScreenRoute)
                }
            }

            // Home Screen
            composable<HomeScreenRoute> {
                selectedIndex = 0
                topBar.value = "Home"
                HomeScreen(
                    onNavigate,
                    onStart = { projects, courses ->
                        currentProjects = projects ?: emptyList()
                        currentCourses = courses ?: emptyList()
                    }
                )
            }

            // Project Item
            composable<ProjectItemRoute>(
                typeMap = mapOf(
                    typeOf<ProjectUiModel>() to CustomNavType.projectItemType
                )
            ) { navBackStackEntry ->
                selectedIndex = 1
                topBar.value = "Project"

                val arguments = navBackStackEntry.toRoute<ProjectItemRoute>()
                ProjectItemScreen(
                    arguments.project
                )
            }

            // All Project Screen
            composable<ProjectsScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<ProjectUiModel>>() to CustomNavType.projectsType
                )
            ) {
                selectedIndex = 1
                topBar.value = "Projects"

                val arguments = it.toRoute<ProjectsScreenRoute>()
                ProjectsScreen(
                    arguments.projects,
                    onNavigate
                )
            }

            // All Courses Screen
            composable<CoursesScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<CourseUiModel>>() to CustomNavType.coursesType
                )
            ) {
                selectedIndex = 2
                topBar.value = "Courses"

                val arguments = it.toRoute<CoursesScreenRoute>()
                CoursesScreen(arguments.courses)
            }

            // Message Screen
            composable<MessageScreenRoute> {
                selectedIndex = 3
                topBar.value = "Message"
                MessageScreen()
            }


            /// For Admin
            composable<TopTitleAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<ContactAndAccountsUiModel>?>() to CustomNavType.topTitleAdminType
                )
            ) {
                topBar.value = "EditTopTitle"

                val arguments = it.toRoute<TopTitleAdminScreenRoute>()
                TopTitleAdminScreen(
                    user = arguments.userName,
                    image = arguments.userImage,
                    job = arguments.jobTitle,
                    allAccounts = arguments.accounts,
                    cv = arguments.cvUrl
                )
            }


            composable<AboutAdminScreenRoute> {
                topBar.value = "EditAbout"
                val arguments = it.toRoute<AboutAdminScreenRoute>()
                AboutAdminScreen(arguments.about ?: "")
            }

            composable<CoursesAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<CourseUiModel>?>() to CustomNavType.coursesAdminType
                )
            ) {
                topBar.value = "EditCourses"

                val arguments = it.toRoute<CoursesAdminScreenRoute>()
                CoursesAdminScreen(arguments.courses ?: emptyList())
            }

            composable<EducationAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<EducationUiModel>?>() to CustomNavType.educationAdminType
                )
            ) {
                topBar.value = "EditEducation"

                val arguments = it.toRoute<EducationAdminScreenRoute>()
                EducationAdminScreen(arguments.educations ?: emptyList())
            }

            composable<TechnologiesAndToolsAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<TechnologyTitleUiModel>?>() to CustomNavType.technologiesAndToolsAdminType,
                    typeOf<List<TechnologyUiModel>?>() to CustomNavType.technologyAdminType
                )
            ) {
                topBar.value = "EditTechnologies"

                val arguments = it.toRoute<TechnologiesAndToolsAdminScreenRoute>()
                TechnologiesAndToolsAdminScreen(arguments.technologiesAndTools)
            }

            composable<SkillsAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<SkillUiModel>?>() to CustomNavType.skillsAdminType
                )
            ) {
                topBar.value = "EditSkills"

                val arguments = it.toRoute<SkillsAdminScreenRoute>()
                SkillsAdminScreen(arguments.skills)
            }

            composable<ProjectsAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<ProjectUiModel>?>() to CustomNavType.projectsAdminType
                )
            ) {
                topBar.value = "EditProjects"

                val arguments = it.toRoute<ProjectsAdminScreenRoute>()
                ProjectAdminScreen(allProjects = arguments.projects ?: emptyList())
            }

            composable<ExperienceAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<ExperienceUiModel>?>() to CustomNavType.experienceAdminType
                )
            ) {
                topBar.value = "EditExperience"

                val arguments = it.toRoute<ExperienceAdminScreenRoute>()
                ExperienceAdminScreen(allExperience = arguments.experience ?: emptyList())
            }


            // Content Admin Screen
            composable<ContentAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<ContactAndAccountsUiModel>?>() to CustomNavType.contentAdminType
                )
            ) {
                topBar.value = "EditContents"

                val arguments = it.toRoute<ContentAdminScreenRoute>()
                ContentsAdminScreen(allContentsTitle = arguments.contentsTitle)
            }

            // Certificate Admin Screen
            composable<CertificateAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<CertificateUiModel>?>() to CustomNavType.certificateAdminType
                )
            ) {
                topBar.value = "EditContents"

                val arguments = it.toRoute<CertificateAdminScreenRoute>()
                CertificatesAdminScreen(allCertificates = arguments.certificates)
            }

            // Content Admin Screen
            composable<VideoAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<VideoPresentationUiModel>?>() to CustomNavType.videoAdminType
                )
            ) {
                topBar.value = "EditContents"

                val arguments = it.toRoute<VideoAdminScreenRoute>()
                VideoPresentationsAdminScreen(allVideos = arguments.videos)
            }


        }
    }
}