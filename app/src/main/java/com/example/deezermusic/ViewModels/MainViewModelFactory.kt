package com.example.deezermusic.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deezermusic.Repository.DataRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: DataRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}