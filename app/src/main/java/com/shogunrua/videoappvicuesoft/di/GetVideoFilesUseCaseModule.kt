package com.shogunrua.videoappvicuesoft.di

import com.shogunrua.videoappvicuesoft.domain.repository.VideoFilesRepository
import com.shogunrua.videoappvicuesoft.domain.usecase.GetVideoFilesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@[Module InstallIn(ViewModelComponent::class)]
class GetVideoFilesUseCaseModule {

    @Provides
    fun provideVideoFilesUseCase(
        repository: VideoFilesRepository,
    ): GetVideoFilesUseCase {
        return GetVideoFilesUseCase(
            repository = repository
        )
    }
}
