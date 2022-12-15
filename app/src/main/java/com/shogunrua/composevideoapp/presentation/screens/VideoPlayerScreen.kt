package com.shogunrua.composevideoapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shogunrua.composevideoapp.presentation.model.VideoPlayerCombineData
import com.shogunrua.composevideoapp.presentation.model.uiState.VideoPlayerUiState
import com.shogunrua.composevideoapp.presentation.viewmodel.VideoFilesViewModel

@Composable
fun VideoPlayerScreen(
    viewModel: VideoFilesViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    VideoPlayerContentHandler(
        uiState = uiState,
    )
}

@Composable
fun VideoPlayerContentHandler(
    uiState: VideoPlayerUiState,
) {
    when (uiState) {
        is VideoPlayerUiState.LoadComplete -> {
            VideoPlayerContent(
                data = uiState.uiData
            )
        }

        is VideoPlayerUiState.LoadInProgress -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }

        is VideoPlayerUiState.Error -> {
        }
    }
}

@Composable
fun VideoPlayerContent(
    data: VideoPlayerCombineData,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.padding(20.dp))

        VideoPlayerBlock(
            videoData = data.videoData,
            listOfVideosData = data.listOfVideosData,
        )

        Spacer(modifier = modifier.padding(16.dp))

        VideoIconsBlock(
            videoData = data.videoData,
            listOfVideosData = data.listOfVideosData,
        )
    }
}
