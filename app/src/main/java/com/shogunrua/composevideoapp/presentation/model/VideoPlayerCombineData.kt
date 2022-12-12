package com.shogunrua.composevideoapp.presentation.model

data class VideoPlayerCombineData(
    val videoData: VideoData = VideoData(),
    val listOfVideosData: ListOfVideosData = ListOfVideosData(),
)
