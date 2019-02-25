package com.tesliukdev.fairgame.screens.setup

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tesliukdev.fairgame.dagger.DaggerCloudComponent
import com.tesliukdev.fairgame.dagger.DaggerLocalComponent
import com.tesliukdev.fairgame.gameconnector.GameConnector
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsCloud
import com.tesliukdev.fairgame.gateway.local.rps.RpsLocal
import java.lang.Exception
import javax.inject.Inject

class SelectGateWayViewModel : ViewModel() {
    @Inject
    lateinit var gameConnector: GameConnector
    var selectedGateWay = MutableLiveData<String>()
    var error = MutableLiveData<String>()
    private val cloudComponent by lazy { DaggerCloudComponent.create() }
    private val localComponent by lazy { DaggerLocalComponent.create() }

    fun setCloud() {
        try {
            gameConnector.gateway = cloudComponent.rpsCloud
            selectedGateWay.value = RpsCloud.gateWayName
        } catch (e: Exception) {
            error.value = "Check your internet connection and try again"
        }

    }

    fun setLocal() {
        gameConnector.gateway = localComponent.rpsLocal
        selectedGateWay.value = RpsLocal.gateWayName
    }

    fun setBluetooth() {
        error.value = "Bluetooth to be implemented"
    }
}