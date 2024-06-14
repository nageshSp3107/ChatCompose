package com.example.chatcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.MainActivityViewModel
import com.example.chatcompose.screens.HomeScreen
import com.example.chatcompose.screens.LoginScreen
import com.example.chatcompose.ui.theme.ChatComposeTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatComposeTheme {
                val mainViewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()
                val firebaseUser = mainViewModel.firebaseAuthUserState.collectAsState().value
                val errorState = mainViewModel.errorState.collectAsState().value
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = if (FirebaseAuth.getInstance().currentUser != null) "home" else "login"
                ) {
                    composable("login") {
                        LoginScreen(errorState, firebaseUser, { email, password ->
                            mainViewModel.login(email, password)
                        }, clearCacheUser = {
                            //Clear cache user
                            mainViewModel.clearCacheUser()
                        }, clearMsg = {
                            //Clear cache error
                            mainViewModel.clearErrorState()
                        }, navigateHome = {
                            navController.navigate("home") {
                                popUpTo("login"){
                                    inclusive = true
                                }
                            }
                        })
                    }
                    composable("home") {
                        HomeScreen(
                            signOutClick = {
                            navController.navigate("login"){
                                popUpTo("home"){
                                    inclusive = true
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}