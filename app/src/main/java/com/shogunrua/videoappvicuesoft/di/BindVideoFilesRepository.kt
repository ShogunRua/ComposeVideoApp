package com.shogunrua.videoappvicuesoft.di

import com.shogunrua.videoappvicuesoft.data.repository.VideoFilesRepositoryImpl
import com.shogunrua.videoappvicuesoft.domain.repository.VideoFilesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@[Module InstallIn(ViewModelComponent::class)]
interface BindVideoFilesRepository {

    @Binds
    fun bindVideoFilesRepository(videoFilesRepositoryImpl: VideoFilesRepositoryImpl): VideoFilesRepository
}

