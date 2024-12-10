package com.serhatuludag.watchlistkeeper.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    val movieName: String,
    val directorName: String,
    val releaseYear: Int,
    val imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

) {

}