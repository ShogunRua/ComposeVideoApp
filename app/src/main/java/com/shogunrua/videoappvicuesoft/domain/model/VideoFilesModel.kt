package com.shogunrua.videoappvicuesoft.domain.model

data class VideoFilesModel(
    val id: String,
    val color: Any,
    val fileUrl: String,
    val group: String,
    val isFavorite: Boolean,
    val posterUrl: String,
    val smallPosterUrl: String,
    val smallThumbnailUrl: String,
    val thumbnailUrl: String,
)
