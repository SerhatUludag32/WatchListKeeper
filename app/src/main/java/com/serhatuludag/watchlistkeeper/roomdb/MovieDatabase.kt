package com.serhatuludag.watchlistkeeper.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serhatuludag.watchlistkeeper.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}