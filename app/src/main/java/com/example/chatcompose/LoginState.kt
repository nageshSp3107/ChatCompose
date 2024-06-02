package com.example.chatcompose

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    var isSuccess: Boolean = false,
    var error:String = "",
    var user:FirebaseUser? = null
)
