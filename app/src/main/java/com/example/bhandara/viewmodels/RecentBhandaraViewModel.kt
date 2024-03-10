package com.example.bhandara.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhandara.models.Bhandara
import com.example.bhandara.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentBhandaraViewModel @Inject constructor(private val myRepository: MyRepository) : ViewModel(){
    private val _recentBhandaraState = MutableStateFlow<List<Bhandara>>(emptyList())
    val recentBhandaraState = _recentBhandaraState

    fun fetchRecentBhandara(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val recentBhandaras = myRepository.getBhandara()
                _recentBhandaraState.value = recentBhandaras
            }catch (e: Exception){
                //Handle Exception
            }
        }
    }
}