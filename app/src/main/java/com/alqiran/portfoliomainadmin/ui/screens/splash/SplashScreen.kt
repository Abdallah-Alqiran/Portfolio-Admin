package com.alqiran.portfoliomainadmin.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.R
import com.alqiran.portfoliomainadmin.theme.PortfolioMainTheme
import com.alqiran.portfoliomainadmin.ui.screens.splash.viewModel.SplashState
import com.alqiran.portfoliomainadmin.ui.screens.splash.viewModel.SplashViewModel

@Composable
fun SplashScreen(onNavigate: (isLoggedInt: Boolean) -> Unit) {

    val splashViewModel: SplashViewModel = hiltViewModel()
    val splashState by splashViewModel.splashState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp),
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Control, edit, and sync your portfolio seamlessly.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    LaunchedEffect(Unit) {
        splashViewModel.checkUserLoggedIn()
    }

    LaunchedEffect(splashState) {
        if (splashState is SplashState.Error) {
            kotlinx.coroutines.delay(5000)
            splashViewModel.checkUserLoggedIn()
        }
    }

    when(splashState) {
        is SplashState.Success -> {
            onNavigate((splashState as SplashState.Success).isLoggedIn)
        }
        is SplashState.Error -> Unit
        SplashState.Loading -> Unit
    }
}


@Preview
@Composable
private fun Prev() {
    PortfolioMainTheme {
        SplashScreen(onNavigate = {})
    }
}