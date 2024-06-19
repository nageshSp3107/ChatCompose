package com.example.chatcompose.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatcompose.ui.theme.ChatComposeTheme
import com.google.firebase.auth.FirebaseUser

@Composable
fun LoginScreen(
    error: String?,
    user: FirebaseUser?,
    onLoginClick: (String, String) -> Unit,
    clearCacheUser: () -> Unit,
    clearMsg: () -> Unit,
    navigateHome: () -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var hasLogin by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = error?.isNotEmpty()) {
        if (!error.isNullOrEmpty()) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            hasLogin = false
            clearMsg()
        }
    }
    LaunchedEffect(key1 = user?.email?.isNotEmpty()) {
        if (user != null && user.email != null) {
            Toast.makeText(context, "User Logged In", Toast.LENGTH_SHORT).show()
            hasLogin = false
            clearCacheUser()
            navigateHome()
        }
    }
    LoginContents(username,
        onUsernameChange = { username = it },
        password,
        onPasswordChange = { password = it },
        keyboardController,
        onShowProgressBar = { hasLogin = it },
        hasLogin,
        onLoginClick
    )
}

@Composable
private fun LoginContents(
    username: String,
    onUsernameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    onShowProgressBar: (Boolean) -> Unit,
    isLoading: Boolean,
    onLoginClick: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.sweepGradient(
                    colors = listOf(Color.Magenta, Color.Cyan)
                )
            )
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.statusBarsPadding())
        Text(
            text = "Welcome!",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Here's your first step with us!",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.weight(0.1f))

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = onUsernameChange,
            label = { Text("Username") })
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            keyboardController?.hide()
            onShowProgressBar(true)
            onLoginClick(username, password)
        }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedVisibility(visible = isLoading) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Preview
@Composable
private fun LoginPreview() {
    Surface {
        LoginScreen("",
            user = null,
            onLoginClick = { _, _ -> },
            clearCacheUser = {},
            clearMsg = {},
            navigateHome = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoginDarkPreview() {
    ChatComposeTheme() {
        Surface {
            LoginScreen("",
                user = null,
                onLoginClick = { _, _ -> },
                clearCacheUser = {},
                clearMsg = {},
                navigateHome = {})
        }
    }
}