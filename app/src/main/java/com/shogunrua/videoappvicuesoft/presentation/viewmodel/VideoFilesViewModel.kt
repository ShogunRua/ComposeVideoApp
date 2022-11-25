package com.shogunrua.videoappvicuesoft.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shogunrua.videoappvicuesoft.domain.model.VideoFilesModel
import com.shogunrua.videoappvicuesoft.domain.repository.VideoFilesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoFilesViewModel @Inject constructor(
    private val videoFilesRepository: VideoFilesRepository,
) : ViewModel() {

    val videoFilesList = mutableStateOf<List<VideoFilesModel>>(emptyList())

    init {
        getVideoFiles()
    }

    private fun getVideoFiles() {
        viewModelScope.launch(Dispatchers.IO) {
            videoFilesRepository.getVideoFiles().fold(
                ifLeft = {
                    Log.d("Failure", "getVideoFilesFail")
                },
                ifRight = {
                    videoFilesList.value = it
                    Log.d("VideoFilesViewModel", "getVideoFiles: $it")
                }
            )
        }
    }
}
