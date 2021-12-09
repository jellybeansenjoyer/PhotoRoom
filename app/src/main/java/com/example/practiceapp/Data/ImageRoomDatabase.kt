package com.example.practiceapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ImageEntity::class),version = 1,exportSchema = false)
abstract class ImageRoomDatabase : RoomDatabase(){

    abstract fun getImageDao() : ImageDao

    //Prepopulation of Data in the database
    class ImageRoomCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let{database->
                scope.launch {
                    database.getImageDao().deleteAllImages()
                    database.getImageDao().insertImage(ImageEntity("https://data.whicdn.com/images/343113652/original.jpg"))
                }
            }
        }
    }

    companion object{
        @Volatile
        private var INSTANCE : ImageRoomDatabase?= null
        fun getImageRoomDatabase(scope:CoroutineScope, context: Context):ImageRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,ImageRoomDatabase::class.java,"image_table").addCallback(ImageRoomCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}