package com.voyager.network

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

interface RemoteProvider {

    enum class RequestType {
        GET, POST,
    }

    enum class ContentType {
        JSON,
    }

    interface Request {
        val url: String
        val params: Map<String, String>
        val type: RequestType
        val contentType: ContentType
    }

    sealed class Result {
        data class Success<T>(val response: T) : Result()
        data class Error<T>(val code: Int, val reason: T) : Result()
    }

    fun validateRequest(request: Request)
}


inline fun <reified T : Serializable, reified E : Serializable> RemoteProvider.make(request: RemoteProvider.Request): RemoteProvider.Result {
    validateRequest(request)
    val connection = URL(request.url).openConnection() as HttpURLConnection
    connection.doOutput = true
    connection.setChunkedStreamingMode(0)
    return try {
        RemoteProvider.Result.Success(Json.decodeFromStream<T>(BufferedInputStream(connection.inputStream)))
    } catch (e: IOException) {
        RemoteProvider.Result.Error(-1, Json.decodeFromStream<E>(connection.errorStream))
    } catch (e: Exception) {
        RemoteProvider.Result.Error(HttpURLConnection.HTTP_NOT_IMPLEMENTED, "")
    } finally {
        connection.disconnect()
    }
}


