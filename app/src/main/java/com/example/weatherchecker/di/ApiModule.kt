package com.example.weatherchecker.di

import android.app.Application
import com.example.data.api.WeatherApi
import com.example.weatherchecker.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideCache(application: Application) = Cache(application.cacheDir, BuildConfig.CACHE_SIZE)

    @Provides
    @Singleton
    @Named("authInterceptor")
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalUrl = chain.request().url()
            val modifiedUrl = originalUrl.newBuilder()
                .addQueryParameter("appid", BuildConfig.API_KEY_OWM)
                .build()
            val newRequest = chain.request().newBuilder()
                .url(modifiedUrl)
                .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache,
                            @Named("authInterceptor") authInterceptor: Interceptor,
                            @Named("loggingInterceptor") loggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)


}