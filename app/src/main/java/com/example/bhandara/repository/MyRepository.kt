package com.example.bhandara.repository

import com.example.bhandara.models.Bhandara
import com.example.bhandara.models.Post
import com.example.bhandara.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MyRepository @Inject constructor(private val firestore: FirebaseFirestore) {
    val firebaseAuth = FirebaseAuth.getInstance()
    suspend fun fetchPosts(): List<Post> {
        return try {
            val querySnapshot = firestore.collection("Posts")
                .get()
                .await()

            val posts = mutableListOf<Post>()
            for (document in querySnapshot.documents) {
                val post = document.toObject(Post::class.java)
                post?.let { posts.add(it) }
            }

            posts
        } catch (e: Exception) {
            // Handle exceptions or logging as needed
            emptyList()
        }
    }

    suspend fun fetchUser(userid: String): User {

        val documentSnapshot = firestore.collection("Users").document(userid).get().await()
        return documentSnapshot.toObject(User::class.java) ?: User()
    }

    suspend fun getBhandara(): List<Bhandara>{
        return try {
            val querySnapshot = firestore.collection("Bhandara")
                .get()
                .await()

            val bhandaraList = mutableListOf<Bhandara>()
            for (document in querySnapshot.documents) {
                val bhandara = document.toObject(Bhandara::class.java)
                bhandara?.let { bhandaraList.add(it) }
            }
            bhandaraList

        }catch (e: Exception){
            emptyList()
        }
    }
}