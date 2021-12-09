package com.example.practiceapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageEntity(@PrimaryKey @ColumnInfo(name = "image_url")var imageUrl : String)