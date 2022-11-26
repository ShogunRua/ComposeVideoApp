package com.shogunrua.videoappvicuesoft.presentation.model

import androidx.compose.runtime.mutableStateOf
import com.shogunrua.videoappvicuesoft.domain.utils.EMPTY_STRING

class VideoData(
    videoIsReadyInit: Boolean = false,
    videoFileInit: String = EMPTY_STRING,
) {
    val videoIsReady = mutableStateOf(videoIsReadyInit)
    val videoFile = mutableStateOf(videoFileInit)
}
