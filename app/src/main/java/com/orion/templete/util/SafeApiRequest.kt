package com.orion.templete.util

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> safeApiRequest(call: suspend () -> Response<T>): T {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            } ?: throw Exception("Response body is null")
        } else {
            val errorMessage = parseError(response)
            throw Exception(errorMessage)
        }
    }

    private fun parseError(response: Response<*>): String {
        val errorBody = response.errorBody()?.string()
        return if (!errorBody.isNullOrEmpty()) {
            try {
                JSONObject(errorBody).getString("error")
            } catch (e: Exception) {
                "Network error occurred"
            }
        } else {
            "Network error occurred"
        }
    }

}