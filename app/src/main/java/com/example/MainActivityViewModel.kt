package com.example

import androidx.lifecycle.ViewModel
import com.example.chatcompose.LoginState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel: ViewModel() {
    val loginState = MutableStateFlow(LoginState())
    fun login(email:String, password:String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnSuccessListener {task->
            if (task.user!=null){
                loginState.update {
                    it.copy(true, "", it.user)
                }
            }else{
                loginState.update {
                    it.copy(false, "Something is wrong!", null)
                }
            }
        }.addOnFailureListener {ex->
            loginState.update {
                it.copy(false, ex.printStackTrace().toString(), null)
            }
        }
    }
}