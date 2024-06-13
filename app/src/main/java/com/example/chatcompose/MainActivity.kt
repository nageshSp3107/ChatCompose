package com.example.chatcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
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
                val loginState = mainViewModel.loginState.collectAsState().value
                val errorState = mainViewModel.errorState.collectAsState().value
                Column {
                    LoginScreen(errorState, { email, password ->
                        mainViewModel.login(email, password)
                    },{
                      mainViewModel.clearErrorState()
                    })
                }
            }
        }
    }
}