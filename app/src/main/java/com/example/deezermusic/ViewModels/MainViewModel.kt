package com.example.deezermusic.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.deezermusic.Models.Data
import com.example.deezermusic.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deezermusic.Models.MyData
import com.example.deezermusic.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    val dataLiveData: LiveData<MyData>
        get() = repository.data

    init {
        viewModelScope.launch {
            repository.getData()
        }
    }
}*/

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    fun getData(query: String): LiveData<PagingData<Data>> {
        return repository.getData(query).cachedIn(viewModelScope)
    }

}