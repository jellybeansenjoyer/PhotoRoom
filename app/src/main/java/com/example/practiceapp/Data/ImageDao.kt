package com.example.practiceapp.Data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Query("SELECT * FROM image_table")
    fun getAllImages(): Flow<List<ImageEntity>>

    @Query("DELETE FROM image_table")
    suspend fun deleteAllImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImage(image: ImageEntity)

    @Delete
    suspend fun deleteThis(image: ImageEntity)
}