package com.tesliukdev.fairgame.gateway.cloud.rps

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RpsApi {

    @POST("/json-rpc/1/invoke")
    fun getMove(@Body request: RpsRequest): Single<RpsResponse>
}