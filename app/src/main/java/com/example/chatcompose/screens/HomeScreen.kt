package com.example.chatcompose.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatcompose.componets.BottomAppBar
import com.example.chatcompose.ui.theme.ChatComposeTheme
import com.example.chatcompose.utilty.Routes

@Composable
fun HomeScreen(signOutClick:() -> Unit) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(bottomBarNavController = bottomNavController)
        }
    ){
        innerPadding ->
        Box(modifier = Modifier.
            statusBarsPadding().navigationBarsPadding().
        padding(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())){
            NavHost(navController = bottomNavController, startDestination = Routes.HOME){
                composable(Routes.HOME){
                    ChatsScreen()
                }
                composable(Routes.PROFILE){
                    ProfileScreen()
                }
                composable(Routes.SETTINGS){
                    SettingsScreen(signOutClick)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ChatComposeTheme {
        Surface {
            HomeScreen(signOutClick = {})
        }
    }

}