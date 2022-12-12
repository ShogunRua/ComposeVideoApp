package com.shogunrua.composevideoapp.presentation.model

import androidx.compose.runtime.mutableStateOf
import com.shogunrua.composevideoapp.domain.utils.EMPTY_STRING
import com.shogunrua.composevideoapp.domain.utils.INT_0

class VideoData(
    videoFileInit: String = EMPTY_STRING,
    videoCurrentIndexInit: Int = INT_0,
    textIsVisibleInit: Boolean = false,
) {
    val videoFile = mutableStateOf(videoFileInit)
    val videoCurrentIndex = mutableStateOf(videoCurrentIndexInit)
    val textIsVisible = mutableStateOf(textIsVisibleInit)
}
