package com.shogunrua.composevideoapp.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoFilesItem(
    @SerialName("color")
    val color: String?,
    @SerialName("file_url")
    val fileUrl: String,
    @SerialName("group")
    val group: String,
    @SerialName("id")
    val id: String,
    @SerialName("is_favorite")
    val isFavorite: Boolean,
    @SerialName("poster_url")
    val posterUrl: String,
    @SerialName("small_poster_url")
    val smallPosterUrl: String,
    @SerialName("small_thumbnail_url")
    val smallThumbnailUrl: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
)
