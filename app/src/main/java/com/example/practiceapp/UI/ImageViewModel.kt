package com.example.practiceapp.UI

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.practiceapp.Data.ImageEntity
import com.example.practiceapp.Data.ImageRepository
import kotlinx.coroutines.launch

class ImageViewModel(private val repo:ImageRepository) : ViewModel() {

    val images: LiveData<List<ImageEntity>> = repo.allImages.asLiveData()

    fun deleteThisImage(imageEntity: ImageEntity) = viewModelScope.launch{
            repo.deleteThisImage(imageEntity)
        }

    fun deleteAllImages() = viewModelScope.launch {
        repo.deleteEverything()
    }

    fun insertThisImage(imageEntity:ImageEntity) = viewModelScope.launch {
        repo.insertThisImage(imageEntity)
    }
}