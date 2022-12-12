package com.shogunrua.composevideoapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shogunrua.composevideoapp.domain.usecase.GetVideoFilesUseCase
import com.shogunrua.composevideoapp.presentation.model.ListOfVideosData
import com.shogunrua.composevideoapp.presentation.model.VideoData
import com.shogunrua.composevideoapp.presentation.model.VideoPlayerCombineData
import com.shogunrua.composevideoapp.presentation.model.uiState.VideoPlayerUiState
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
                    delay(3.seconds)
                    videosData.value.videoFile.value =
                        listOfVideosData.value.listOfVideos.value[0].fileUrl
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
                _uiState.value = VideoPlayerUiState.LoadComplete(videoFilesUiData)
            }
        }
    }
}
