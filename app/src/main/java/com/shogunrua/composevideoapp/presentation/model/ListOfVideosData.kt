package com.shogunrua.composevideoapp.presentation.model

import androidx.compose.runtime.mutableStateOf
import com.shogunrua.composevideoapp.domain.model.VideoFilesModel

class ListOfVideosData(
    listOfVideosInit: List<VideoFilesModel> = emptyList(),
) {
    val listOfVideos = mutableStateOf(listOfVideosInit)
}
