package com.example.chatcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatcompose.ui.theme.ChatComposeTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(signOutClick:() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.sweepGradient(
                    colors = listOf(Color.Magenta, Color.Cyan)
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome!", style = TextStyle(
            color = MaterialTheme.colorScheme.background,
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium
        ))
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            signOutClick()
        }) {
            Text(text = "Sign Out")
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