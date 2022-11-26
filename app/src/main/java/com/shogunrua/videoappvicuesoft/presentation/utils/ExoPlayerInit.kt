package com.shogunrua.videoappvicuesoft.presentation.utils

import android.content.Context
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.ExoPlayer

object ExoPlayerInit {
    lateinit var cacheDataSourceFactory: CacheDataSource.Factory
        private set

    fun initCache(cacheDataSourceFactory: CacheDataSource.Factory) {
        this.cacheDataSourceFactory = cacheDataSourceFactory
    }

    fun initEmptyPlayer(
        context: Context,
        doBeforeBuild: ExoPlayer.Builder.() -> ExoPlayer.Builder = { this },
    ): ExoPlayer {
        return ExoPlayer.Builder(context)
            .doBeforeBuild()
            .build()
    }
}

