package com.example.practiceapp.Data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ImageRepository(private val dao: ImageDao) {

       val allImages : Flow<List<ImageEntity>> = dao.getAllImages()

        @WorkerThread
        suspend fun deleteThisImage(imageEntity: ImageEntity)
        {
            dao.deleteThis(imageEntity)
        }

        @WorkerThread
        suspend fun deleteEverything()
        {
            dao.deleteAllImages()
        }

        @WorkerThread
        suspend fun insertThisImage(imageEntity: ImageEntity){
            dao.insertImage(imageEntity)
        }

}