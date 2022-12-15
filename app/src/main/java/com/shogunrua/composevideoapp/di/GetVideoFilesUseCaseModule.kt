package com.shogunrua.composevideoapp.di

import com.shogunrua.composevideoapp.domain.usecase.GetMediaItemsUseCase
import com.shogunrua.composevideoapp.domain.usecase.GetVideoFilesUseCase
import com.shogunrua.composevideoapp.domain.usecase.VideoFilesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@[Module InstallIn(ViewModelComponent::class)]
class VideoFilesUseCaseModule {

    @Provides
    fun provideVideoFilesUseCase(
        getVideoFilesUseCase: GetVideoFilesUseCase,
        getMediaItemsUseCase: GetMediaItemsUseCase,
    ): VideoFilesUseCases {
        return VideoFilesUseCases(
            getVideoFilesUseCase = getVideoFilesUseCase,
            getMediaItemsUseCase = getMediaItemsUseCase,
        )
    }
}
