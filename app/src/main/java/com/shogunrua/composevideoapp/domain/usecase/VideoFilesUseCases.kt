package com.shogunrua.composevideoapp.domain.usecase

import javax.inject.Inject

data class VideoFilesUseCases @Inject constructor(
    val getVideoFilesUseCase: GetVideoFilesUseCase,
    val getMediaItemsUseCase: GetMediaItemsUseCase,
)
