package com.example.bhandara.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bhandara.models.User
import com.example.bhandara.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: AuthRepository
) : ViewModel(){
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> get() = _currentUser
    suspend fun registerUser(user: User): Boolean {
        return userRepository.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return userRepository.loginUser(email, password)
    }
}