package com.shogunrua.composevideoapp.data.network.api

import arrow.core.Either
import com.shogunrua.composevideoapp.data.network.response.VideoFilesResponse
import com.shogunrua.composevideoapp.data.network.utils.ApiCallWrapper
import com.shogunrua.composevideoapp.data.network.utils.Failure
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class VideoFilesApi @Inject constructor(
    private val httpClient: HttpClient,
    private val apiCallWrapper: ApiCallWrapper,
) : KtorApi<VideoFilesResponse> {

    override suspend fun execute(): Either<Failure, VideoFilesResponse> {
        return apiCallWrapper.safeApiCall {
            httpClient.get("api/backgrounds/") {
                parameter("group", "video")
                parameter("category_id", "1")
            }.body()
        }
    }
}
