package com.shogunrua.videoappvicuesoft.data.network.utils

import com.shogunrua.videoappvicuesoft.data.network.response.VideoFilesItem
import com.shogunrua.videoappvicuesoft.data.network.response.VideoFilesResponse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object VideoFilesSerializer : KSerializer<VideoFilesResponse> {
    private val serializer = ListSerializer(VideoFilesItem.serializer())

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: VideoFilesResponse) {
        val rowValues = value.videoFiles
        rowValues.let { rowValues }
            ?.let { encoder.encodeSerializableValue(serializer, it) }
    }

    override fun deserialize(decoder: Decoder): VideoFilesResponse {
        return VideoFilesResponse(decoder.decodeSerializableValue(serializer))
    }
}
