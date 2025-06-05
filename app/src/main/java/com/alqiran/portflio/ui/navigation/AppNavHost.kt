package com.alqiran.portflio.ui.navigation

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
import com.alqiran.portflio.ui.components.bars.BottomBar
import com.alqiran.portflio.ui.components.bars.TopBar
import com.alqiran.portflio.ui.screens.courses_screen.CoursesScreen
import com.alqiran.portflio.ui.screens.home_screen.HomeScreen
import com.alqiran.portflio.ui.screens.message_screen.MessageScreen
import com.alqiran.portflio.ui.screens.project_item_screen.ProjectItemScreen
import com.alqiran.portflio.ui.screens.projects_screen.ProjectsScreen
import com.alqiran.portflio.ui.screens.splash.SplashScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    val topBar = remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(-1) }


    val onNavigate: (NavigationAction) -> Unit = { action ->
        when (action) {
            is NavigationAction.ToProject -> {
                navController.navigate(Screens.ProjectItemRoute.passId(action.projectId))
            }

            is NavigationAction.ToViewAllCourses -> {
                navController.navigate(Screens.CoursesScreenRoute.route)
            }
            is NavigationAction.ToViewAllProjects -> {
                navController.navigate(Screens.ProjectsScreenRoute.route)
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
                                navController.navigate(Screens.HomeScreenRoute.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                            1 -> navController.navigate(Screens.ProjectsScreenRoute.route)
                            2 -> navController.navigate(Screens.CoursesScreenRoute.route)
                            3 -> navController.navigate(Screens.MessageScreenRoute.route)
                        }
                    },
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.SplashScreenRoute.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            // Splash
            composable(Screens.SplashScreenRoute.route) {
                selectedIndex = -1
                topBar.value = "Splash"
                SplashScreen {
                    navController.navigate(Screens.HomeScreenRoute.route)
                }
            }

            // Home Screen
            composable(Screens.HomeScreenRoute.route) {
                selectedIndex = 0
                topBar.value = "Home"
                HomeScreen(
                    onNavigate
                )
            }

            // Project Item
            composable(Screens.ProjectItemRoute.route) { navBackStackEntry ->
                selectedIndex = 1
                topBar.value = "Project"
                val projectId = navBackStackEntry.arguments?.getString("project_id")

                ProjectItemScreen (
                    projectId!!.toInt()
                )
            }

            // All Project Screen
            composable(Screens.ProjectsScreenRoute.route) {
                selectedIndex = 1
                topBar.value = "Projects"
                ProjectsScreen(
                    onNavigate
                )
            }

            // All Courses Screen
            composable(Screens.CoursesScreenRoute.route) {
                selectedIndex = 2
                topBar.value = "Courses"
                CoursesScreen()
            }

            // Message Screen
            composable(Screens.MessageScreenRoute.route) {
                selectedIndex = 3
                topBar.value = "Message"
                MessageScreen()
            }
        }
    }
}