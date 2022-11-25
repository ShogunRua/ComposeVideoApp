package com.shogunrua.videoappvicuesoft.di

import com.shogunrua.videoappvicuesoft.data.network.utils.ApiCallWrapper
import com.shogunrua.videoappvicuesoft.data.network.utils.VideoAppLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class AppModule {

    @[Provides Singleton]
    fun provideKtorClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging) {
                logger = VideoAppLogger()
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                }

                header(ACCEPT, APP_JSON)
                header(CONTENT_TYPE, APP_JSON)
            }

            engine {
                config {
                    connectTimeout(1, TimeUnit.MINUTES)
                    readTimeout(30, TimeUnit.SECONDS)
                    writeTimeout(30, TimeUnit.SECONDS)
                }
            }
        }
    }

    companion object {
        private const val BASE_URL =
            "dev.bgrem.deelvin.com"
        private const val ACCEPT = "Accept"
        private const val CONTENT_TYPE = "Content-Type"
        private const val APP_JSON = "application/json"
    }

    @[Provides Singleton]
    fun providesApiCallWrapper() = ApiCallWrapper()
}
