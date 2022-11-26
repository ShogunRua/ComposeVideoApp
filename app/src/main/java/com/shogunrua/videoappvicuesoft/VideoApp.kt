package com.shogunrua.videoappvicuesoft

import android.app.Application
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import com.shogunrua.videoappvicuesoft.presentation.utils.ExoPlayerUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VideoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initExoPlayerCache()
    }

    private fun initExoPlayerCache() {
        val exoPlayerCacheSize: Long = 90 * 1024 * 1024
        val leastRecentlyUsedCacheEvictor = LeastRecentlyUsedCacheEvictor(exoPlayerCacheSize)
        val exoDatabaseProvider = StandaloneDatabaseProvider(this)
        val simpleCache = SimpleCache(cacheDir, leastRecentlyUsedCacheEvictor, exoDatabaseProvider)

        val httpDataSourceFactory = DefaultHttpDataSource
            .Factory()
            .setAllowCrossProtocolRedirects(true)

        ExoPlayerUtil.initCache(
            cacheDataSourceFactory = CacheDataSource.Factory()
                .setCache(simpleCache)
                .setUpstreamDataSourceFactory(httpDataSourceFactory)
                .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
        )
    }
}
