package com.shogunrua.videoappvicuesoft.presentation.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shogunrua.videoappvicuesoft.domain.usecase.GetVideoFilesUseCase
import com.shogunrua.videoappvicuesoft.presentation.model.ListOfVideosData
import com.shogunrua.videoappvicuesoft.presentation.model.VideoData
import com.shogunrua.videoappvicuesoft.presentation.model.VideoPlayerCombineData
import com.shogunrua.videoappvicuesoft.presentation.model.VideoPlayerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class VideoFilesViewModel @Inject constructor(
    private val getVideoFilesUseCase: GetVideoFilesUseCase,
) : ViewModel() {

    private var _uiState =
        MutableStateFlow<VideoPlayerUiState>(VideoPlayerUiState.LoadInProgress)
    val uiState: StateFlow<VideoPlayerUiState> = _uiState.asStateFlow()

    private val listOfVideosData = MutableStateFlow(ListOfVideosData())
    private val videosData = MutableStateFlow(VideoData())

    init {
        getVideoFiles()
    }

    private fun getVideoFiles() {
        viewModelScope.launch {
            getVideoFilesUseCase.getVideoFiles().fold(
                ifLeft = {
                    Log.d("Failure", "getVideoFilesFail")
                },
                ifRight = {
                    listOfVideosData.value.listOfVideos.value = it
                    buildVideoFilesUiData()
                    Log.d("VideoFilesViewModel", "getVideoFiles: $it")
                }
            )
        }
    }

    private fun buildVideoFilesUiData() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                listOfVideosData,
                videosData,
            ) { listOfVideosData, videosData ->
                VideoPlayerCombineData(
                    listOfVideosData = listOfVideosData,
                    videoData = videosData,
                )
            }.collect { videoFilesUiData ->
                videosData.value.videoFile.value = listOfVideosData.value.listOfVideos.value[0].fileUrl
                delay(5.seconds)
                _uiState.value = VideoPlayerUiState.LoadComplete(videoFilesUiData)
            }
        }
    }
}
