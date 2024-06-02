package com.example.chatcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import com.example.MainActivityViewModel
import com.example.chatcompose.screens.LoginScreen
import com.example.chatcompose.ui.theme.ChatComposeTheme
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatComposeTheme {
                val mainViewModel:MainActivityViewModel by viewModels<MainActivityViewModel>()
                val loginState = mainViewModel.loginState.asStateFlow().collectAsState()
                Column {
                    LoginScreen(loginState) { email, password ->
                        mainViewModel.login(email, password)
                    }
                    AnimatedVisibility(visible = loginState.value.error.isEmpty()) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}