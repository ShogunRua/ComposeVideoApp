package com.shogunrua.composevideoapp.data.network.utils

import android.util.Log
import io.ktor.client.plugins.logging.*

class VideoAppLogger : Logger {

    override fun log(message: String) {
        Log.d(this::class.simpleName, message)
    }
}
