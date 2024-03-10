package com.example.bhandara.repository

import com.example.bhandara.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun registerUser(user: User): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            saveUserToFirestore(user)
            true
        } catch (e: Exception) {
            // Handle registration failure
            false
        }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            // Handle login failure
            false
        }
    }
    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("Users").document(user.email).set(user).await()
    }
    private fun updateUserInfo(authResult: AuthResult) {
        val profile = UserProfileChangeRequest.Builder()
        //profile.displayName =name.text.toString()
        authResult.user?.updateProfile(profile.build())
    }
}