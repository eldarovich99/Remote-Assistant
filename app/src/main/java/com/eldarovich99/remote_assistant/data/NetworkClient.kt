package com.eldarovich99.remote_assistant.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient {
    companion object{
        val BASE_URL = "https://www.innerfidelity.com/images/"

        private var retrofit: Retrofit? = null

        fun <T> getApi(api: Class<T>): T {
            return mainManagerRetroClient.create(api)
        }

        private val mainManagerRetroClient: Retrofit
            get() {

                //cookie.setCookie(null)
                val logging = HttpLoggingInterceptor()
                logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }

                val httpClient = OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    //.cookieJar(cookie)
                    .build()

                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient)
                        .build()
                }
                return retrofit!!
            }
    }

}