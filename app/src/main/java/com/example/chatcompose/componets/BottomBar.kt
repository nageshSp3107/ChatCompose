package com.example.chatcompose.componets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomAppBar(bottomBarNavController: NavHostController) {
    val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
    ) {
        BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = currentRoute == navigationItem.route,
                label = {
                    Text(navigationItem.label)
                },
                icon = {
                    Icon(
                        imageVector = navigationItem.icon,
                        contentDescription = navigationItem.label
                    )
                }, onClick = {
                    bottomBarNavController.navigate(navigationItem.route) {
                       /* navBackStackEntry?.destination?.route?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }*/
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                            saveState = true

                        }
                    }
                })
        }
    }
}