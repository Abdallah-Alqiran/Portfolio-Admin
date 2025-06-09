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
import com.alqiran.portfoliomainadmin.ui.model.ContactAndAccountsUiModel
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.toptitle_admin.TopTitleAdminScreen
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
                navController.navigate(TopTitleAdminScreenRoute(userName = action.userName, userImage = action.userImage, jobTitle = action.jobTitle, accounts = action.accounts, cvUrl = action.cvUrl))
            }
            NavigationAction.Nothing -> {}
        }
    }


    Scaffold(
        topBar = {
            when(topBar.value) {
                "Home" -> { TopBar("Home", onClick = {}) }
                "Project" -> { TopBar("Project", onClick = { navController.popBackStack() }) }
                "Projects" -> { TopBar("Projects", onClick = { navController.popBackStack() }) }
                "Courses" -> { TopBar("Courses", onClick = { navController.popBackStack() }) }
                "Message" -> { TopBar("Contact Me", onClick = { navController.popBackStack() }) }
                "TopTitleAdmin" -> { TopBar("Top Title Admin", onClick = { navController.popBackStack() }) }
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
                        currentProjects = projects?: emptyList()
                        currentCourses = courses?: emptyList()
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
                ProjectItemScreen (
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

            // topTitle
            composable<TopTitleAdminScreenRoute>(
                typeMap = mapOf(
                    typeOf<List<ContactAndAccountsUiModel>?>() to CustomNavType.topTitleAdminType
                )
            ) {
                topBar.value = "TopTitleAdmin"

                val arguments = it.toRoute<TopTitleAdminScreenRoute>()
                TopTitleAdminScreen(
                    user = arguments.userName,
                    image = arguments.userImage,
                    job = arguments.jobTitle,
                    allAccounts = arguments.accounts,
                    cv = arguments.cvUrl
                )
            }
        }
    }
}