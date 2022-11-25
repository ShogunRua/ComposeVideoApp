package com.shogunrua.videoappvicuesoft.data.network.response

import com.shogunrua.videoappvicuesoft.data.network.utils.VideoFilesSerializer
import kotlinx.serialization.Serializable

@Serializable(with = VideoFilesSerializer::class)
data class VideoFilesResponse(
    val videoFiles: List<VideoFilesItem>,
)
