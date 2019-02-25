package com.tesliukdev.fairgame.gateway.cloud.rps

import com.tesliukdev.fairgame.gateway.Gateway
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RpsCloud
@Inject
constructor(private var rpsApi: RpsApi) : Gateway {

    companion object {
        val gateWayName: String = RpsCloud::class.java.simpleName
    }

    override fun getMove(): Single<String> {
        return rpsApi.getMove(RpsRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t: RpsResponse -> t.result?.random?.data?.get(0)}
                .map { t: Int -> t.toString() }
    }

    override fun sendMove(move: String) {
        //Game mode: with computer. No need to send move
    }
}