package com.maschago.neugelbchallangeandroid.data


import com.maschago.neugelbchallangeandroid.data.remote.ApiService
import com.maschago.neugelbchallangeandroid.data.remote.MoviePagingSource
import com.maschago.neugelbchallangeandroid.data.remote.MovieSearchPagingSource
import com.maschago.neugelbchallangeandroid.data.remote.TokenInterceptor
import com.maschago.neugelbchallangeandroid.data.repository.MainRepositoryImpl
import com.maschago.neugelbchallangeandroid.domain.MainRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }
    factory { TokenInterceptor() }
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<TokenInterceptor>())
            if (BuildConfig.DEBUG) {
                addInterceptor(get<HttpLoggingInterceptor>())
            }
        }.connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).build()
    }

    single {

        val okHttpClient: OkHttpClient = get()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    single<ApiService> {
        val retrofit: Retrofit = get()
        retrofit.create(ApiService::class.java)
    }

    single<MainRepository> {
        MainRepositoryImpl(apiService = get())
    }

    single<MoviePagingSource> {
        MoviePagingSource(apiService = get())
    }

    single<MovieSearchPagingSource> {
        MovieSearchPagingSource(apiService = get(), get())
    }
}