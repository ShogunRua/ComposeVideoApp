package com.shogunrua.composevideoapp.presentation.model.uiState

import com.shogunrua.composevideoapp.data.network.utils.Failure
import com.shogunrua.composevideoapp.presentation.model.VideoPlayerCombineData

sealed class VideoPlayerUiState {
    data class LoadComplete(val uiData: VideoPlayerCombineData) : VideoPlayerUiState()
    object LoadInProgress : VideoPlayerUiState()
    data class Error(val failure: Failure) : VideoPlayerUiState()
}
