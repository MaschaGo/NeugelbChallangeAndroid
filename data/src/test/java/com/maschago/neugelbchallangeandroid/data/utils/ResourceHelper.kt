package com.maschago.neugelbchallangeandroid.data.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

    requireNotNull(inputStream) { "File not found: $fileName" } // Ensure file exists

    val source = inputStream.source().buffer()
    val response = MockResponse()
        .setResponseCode(code)
        .setBody(source.readString(StandardCharsets.UTF_8))

    enqueue(response)
}