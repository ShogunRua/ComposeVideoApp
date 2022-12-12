package com.shogunrua.composevideoapp.data.network.response

import com.shogunrua.composevideoapp.data.network.utils.VideoFilesSerializer
import kotlinx.serialization.Serializable

@Serializable(with = VideoFilesSerializer::class)
data class VideoFilesResponse(
    val videoFiles: List<VideoFilesItem>,
)
