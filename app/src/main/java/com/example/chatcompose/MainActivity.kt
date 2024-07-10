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
import com.example.chatcompose.utilty.Routes
import com.google.firebase.auth.FirebaseAuth

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
                    startDestination = FirebaseAuth.getInstance().currentUser?.let { Routes.HOME } ?: Routes.LOGIN
                ) {
                    composable(Routes.LOGIN) {
                        LoginScreen(errorState, firebaseUser, { email, password ->
                            mainViewModel.login(email, password)
                        }, clearCacheUser = {
                            //Clear cache user
                            mainViewModel.clearCacheUser()
                        }, clearMsg = {
                            //Clear cache error
                            mainViewModel.clearErrorState()
                        }, navigateHome = {
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.LOGIN){
                                    inclusive = true
                                }
                            }
                        })
                    }
                    composable(Routes.HOME) {
                        HomeScreen(
                            signOutClick = {
                            navController.navigate(Routes.LOGIN){
                                popUpTo(Routes.HOME){
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