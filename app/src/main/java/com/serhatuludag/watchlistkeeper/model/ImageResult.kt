package com.serhatuludag.watchlistkeeper.model

import com.google.gson.annotations.SerializedName

data class ImageResult(
    val id: Int,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val comments: Int,
    @SerializedName("user_id")
    val userID: Int,
    val user : String,
    val userImageURL : String
)
