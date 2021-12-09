package com.example.practiceapp.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practiceapp.Data.ImageRepository
import java.lang.IllegalArgumentException

class ImageViewModelFactory(private val repository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ImageViewModel::class.java))
            return ImageViewModel(repository) as T

        throw IllegalArgumentException("Wrong ViewModel entered")
    }
}