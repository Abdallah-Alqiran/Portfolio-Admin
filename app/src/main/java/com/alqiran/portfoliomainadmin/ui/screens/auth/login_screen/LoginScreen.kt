package com.alqiran.portfoliomainadmin.ui.screens.auth.login_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.ui.components.CustomOutlinedTextFieldWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.screens.auth.login_screen.viewModel.LoginState
import com.alqiran.portfoliomainadmin.ui.screens.auth.login_screen.viewModel.LoginViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun LoginScreen(onLogin: () -> Unit, goToSignUp: () -> Unit) {

    val loginViewModel: LoginViewModel = hiltViewModel()
    val state by loginViewModel.state.collectAsStateWithLifecycle()


    val email by loginViewModel.email.collectAsStateWithLifecycle()
    val password by loginViewModel.password.collectAsStateWithLifecycle()

    val invalidInput by loginViewModel.invalidElements.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            is LoginState.Authenticated -> {
                onLogin()
            }

            is LoginState.Error -> {
                Toast.makeText(
                    context,
                    "Authentication failed. ${(state as LoginState.Error).errorMessage}}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit
        }
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomOutlinedTextFieldWidget(
                textValue = email,
                textLabel = "email",
                placeHolderLabel = "Enter your email...",
                errorMessage = invalidInput["email"]
            ) {
                loginViewModel.onEmailChange(it)
            }

            Spacer(modifier = Modifier.height(12.dp))

            CustomOutlinedTextFieldWidget(
                textValue = password,
                textLabel = "password",
                placeHolderLabel = "Enter your password...",
                errorMessage = invalidInput["password"],
                isPassword = true,
            ) {
                loginViewModel.onPasswordChange(it)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Login button
                DefaultButton(
                    text = "Login",
                    buttonType = ButtonType.UploadOnClick {
                        loginViewModel.logInUser()
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                TextButton(onClick = {
                    goToSignUp()
                }) {
                    Text(
                        text = "Signup",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

        }
    }
}