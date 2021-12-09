package com.example.practiceapp.Data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.supervisorScope

class ImageApplication : Application() {
    val scope = CoroutineScope(SupervisorJob())

        val database by lazy {
        ImageRoomDatabase.getImageRoomDatabase(context=this,scope =scope)
    }
        val repository by lazy{
            ImageRepository(database.getImageDao())
        }
}