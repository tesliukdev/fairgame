package com.tesliukdev.fairgame.dagger

import com.tesliukdev.fairgame.gateway.cloud.rps.RpsApi
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CloudModule {

    companion object {
        const val RPS_URL = "https://api.random.org"
    }

    @Provides
    @Singleton
    internal fun provideRpsApi(connectionPool: ConnectionPool): RpsApi {
        return createApi(RpsApi::class.java, RPS_URL, createClient(connectionPool))
    }

    @Provides
    @Singleton
    internal fun provideConnectionPool(): ConnectionPool {
        return ConnectionPool()
    }

    private fun <T> createApi(apiClass: Class<T>, baseUrl: String, client: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(apiClass)
    }

    private fun createClient(connectionPool: ConnectionPool): OkHttpClient {
        return OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .build()
    }
}