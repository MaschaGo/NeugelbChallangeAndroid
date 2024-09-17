package com.maschago.neugelbchallangeandroid.data.remote

import com.maschago.neugelbchallangeandroid.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = BuildConfig.TMDB_API_KEY
        val url = original.url.newBuilder().addQueryParameter("api_key", token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
