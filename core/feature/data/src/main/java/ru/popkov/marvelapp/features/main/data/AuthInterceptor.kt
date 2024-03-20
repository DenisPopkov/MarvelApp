package ru.popkov.marvelapp.features.main.data

import okhttp3.Interceptor
import okhttp3.Response
import ru.popkov.android.core.feature.data.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

class AuthInterceptor : Interceptor {
    private val md5 = MessageDigest.getInstance("MD5")

    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val privateKey = BuildConfig.PRIVATE_KEY
        val pubKey = BuildConfig.PUBLIC_KEY
        val input = ts + privateKey + pubKey
        val hashByteArray = md5.digest(input.toByteArray())
        val hash = BigInteger(1, hashByteArray).toString(16).padStart(32, '0')

        return chain.request().let {
            val url = it.url.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", pubKey)
                .addQueryParameter("hash", hash)
                .build()

            chain.proceed(it.newBuilder().url(url).build())
        }
    }
}
