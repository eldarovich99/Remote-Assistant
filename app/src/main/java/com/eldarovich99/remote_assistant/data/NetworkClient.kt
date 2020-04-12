package com.eldarovich99.remote_assistant.data

import com.auth0.android.jwt.JWT
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit


class NetworkClient {
    companion object{
        val BASE_URL = "http://78.155.197.233:8080/"

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
                        .addConverterFactory(createGsonConverter())
                        .client(httpClient)
                        .build()
                }
                return retrofit!!
            }

        private fun createGsonConverter(): Converter.Factory {
            val dateJsonDeserializer: JsonDeserializer<JWT?> =
                JsonDeserializer<JWT?> { json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext? ->
                    var date: JWT? = null
                    date = if (json == null) null else JWT(json.asString)
                    date
                }
            val gson = GsonBuilder().registerTypeAdapter(JWT::class.java, dateJsonDeserializer).create()
            return GsonConverterFactory.create(gson)
        }
    }


}