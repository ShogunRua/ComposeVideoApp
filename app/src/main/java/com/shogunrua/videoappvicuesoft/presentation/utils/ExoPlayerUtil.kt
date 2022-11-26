package com.shogunrua.videoappvicuesoft.presentation.utils

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource

object ExoPlayerUtil {
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

    fun getMediaSource(videoURL: String?): MediaSource {
        val mediaItem = MediaItem.fromUri(videoURL.orEmpty())
        return ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(mediaItem)

    }
}

