package com.example.chatcompose.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(signOutClick:()-> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Settings")

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            content = {
                Button(onClick = {
                    FirebaseAuth.getInstance().signOut()
                    signOutClick()
                }) {
                    Text(text = "Sign Out")
                }
            }
        )
    }
}