package org.shop.repositorycheck

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private lateinit var context: Context
    private const val BASE_URL = "https://api.github.com"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${getToken()}")
                .build()
            it.proceed(request)
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getToken(): String {
        return context.resources.getString(R.string.github_token)
    }

    // 컨텍스트 설정 함수
    fun setContext(appContext: Context) {
        context = appContext
    }
}