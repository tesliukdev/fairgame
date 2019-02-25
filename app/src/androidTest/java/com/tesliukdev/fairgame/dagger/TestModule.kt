package com.tesliukdev.fairgame.dagger

import com.nhaarman.mockitokotlin2.mock
import com.tesliukdev.fairgame.gameconnector.GameConnector
import com.tesliukdev.fairgame.gateway.Gateway
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsApi
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsCloud
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestModule: CloudModule() {

    companion object {
        var TEST_URL = ""
    }

    override fun provideRpsApi(connectionPool: ConnectionPool): RpsApi {
        val okHttpClient = OkHttpClient.Builder()
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(TEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(RpsApi::class.java)
    }
}
