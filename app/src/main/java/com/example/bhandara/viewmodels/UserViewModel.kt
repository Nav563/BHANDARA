package com.example.bhandara.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhandara.models.User
import com.example.bhandara.repository.MyRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val myRepository : MyRepository) : ViewModel(){
    private val _userState = MutableStateFlow(User())
    val userState : StateFlow<User> = _userState
    val firebaseAuth = FirebaseAuth.getInstance()
    fun getUserData(userId: String) {
        viewModelScope.launch {
            val user = myRepository.fetchUser(userId)
            _userState.value = user
        }
    }
//    fun fetchUser() {
//        viewModelScope.launch {
//           // _userState.value = myRepository.fetchUser(firebaseAuth.currentUser!!.uid)
//            try {
//                val user = myRepository.fetchUser(firebaseAuth.currentUser!!.uid)
//                _userState.value = user
//            }catch (e: Exception){
//                Log.d("Failed to retrieve user: ", "${e.message}")
//            }
//        }
//    }
}