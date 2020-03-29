package com.ash8h.barcodeshare.util

import android.content.Context
import androidx.annotation.StringRes
import com.ash8h.barcodeshare.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

fun getErrorMessage(context: Context, e: Throwable): CharSequence =
    getErrorMessage(context, e, R.string.error_message_default)

fun getErrorMessage(context: Context, e: Throwable, @StringRes resId: Int): CharSequence =
    getErrorMessage(context, e, context.getString(resId))

fun getErrorMessage(context: Context, e: Throwable, text: CharSequence): CharSequence {
    return when (e) {
        is HttpException -> getMessage(e) ?: context.getString(R.string.error_message_network)
        else -> text
    }
}

private fun getMessage(e: HttpException): String? {
    val body = e.response()?.errorBody()?.string() ?: return null
    try {
        val json = JSONObject(body)
        val error = json.getJSONObject("error")
        val message = error.optString("message")
        if (message != null) {
            return message
        }
        val messages: JSONArray = error.getJSONArray("message")
        if (messages.length() > 0) {
            return messages.getString(0)
        }
    } catch (e: JSONException) {
    }
    return null
}

