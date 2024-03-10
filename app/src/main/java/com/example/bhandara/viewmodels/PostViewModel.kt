package com.example.bhandara.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhandara.models.Post
import com.example.bhandara.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val myRepository : MyRepository) : ViewModel() {
    private val _postsState = MutableStateFlow<List<Post>>(emptyList())
    val postsState = _postsState

    fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val posts = myRepository.fetchPosts()
                _postsState.value = posts
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

}