package com.example.bhandara.repository

import com.example.bhandara.data.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow


interface AuthenticationRepository {

    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>

    fun egisterUser(email: String, password: String) : Flow<Resource<AuthResult>>
}