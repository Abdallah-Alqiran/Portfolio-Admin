package com.alqiran.portfoliomainadmin.ui.screens.auth.register_screen

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
import com.alqiran.portfoliomainadmin.ui.screens.auth.register_screen.viewModel.RegisterState
import com.alqiran.portfoliomainadmin.ui.screens.auth.register_screen.viewModel.RegisterViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun RegisterScreen(onRegister: () -> Unit, goToLogin: () -> Unit) {
    val registerViewModel: RegisterViewModel = hiltViewModel()
    val state by registerViewModel.state.collectAsStateWithLifecycle()


    val email by registerViewModel.email.collectAsStateWithLifecycle()
    val password by registerViewModel.password.collectAsStateWithLifecycle()

    val invalidInput by registerViewModel.invalidElements.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            RegisterState.Authenticated -> {
                onRegister()
            }

            is RegisterState.Error -> {
                Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
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
                text = "Register",
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
                registerViewModel.onEmailChange(it)
            }


            Spacer(modifier = Modifier.height(12.dp))

            CustomOutlinedTextFieldWidget(
                textValue = password,
                textLabel = "password",
                placeHolderLabel = "Enter your password...",
                errorMessage = invalidInput["password"],
                isPassword = true,
            ) {
                registerViewModel.onPasswordChange(it)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DefaultButton(
                    text = "Get Started",
                    buttonType = ButtonType.UploadOnClick {
                        registerViewModel.createUser()
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
                    text = "Already have an account?",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                TextButton(onClick = {
                    goToLogin()
                }) {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}