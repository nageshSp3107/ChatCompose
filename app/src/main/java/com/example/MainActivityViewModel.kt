package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatcompose.LoginState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState:StateFlow<LoginState> get() = _loginState.asStateFlow()
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState:StateFlow<String?> get() = _errorState
    fun login(email:String, password:String){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnSuccessListener {task->
                if (task.user!=null){
                    _loginState.update {
                        it.copy(isSuccess = true, error = "", user = it.user)
                    }
                }else{
                    _errorState.update {
                        "Something is wrong!"
                    }
                }

            }.addOnFailureListener {ex->
                _errorState.update {
                    ex.localizedMessage!!
                }
            }
    }

    fun clearErrorState() {
        _errorState.value = null
    }
}