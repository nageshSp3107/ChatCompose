package com.example

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel: ViewModel() {
    private val _firebaseAuthUserState = MutableStateFlow<FirebaseUser?>(null)
    val firebaseAuthUserState:StateFlow<FirebaseUser?> get() = _firebaseAuthUserState
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState:StateFlow<String?> get() = _errorState
    fun login(email:String, password:String){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnSuccessListener {task->
                if (task.user!=null){
                    _firebaseAuthUserState.update {
                        task.user
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

    fun clearCacheUser() {
        clearErrorState()
        _firebaseAuthUserState.value = null
    }
}