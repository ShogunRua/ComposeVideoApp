package com.shogunrua.videoappvicuesoft.data.network.response


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoFileItem(
    @SerialName("color")
    @Contextual val color: Any,
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
