package com.tesliukdev.fairgame.screens.setup

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tesliukdev.fairgame.gameconnector.GameConnector
import com.tesliukdev.fairgame.gateway.Cloud
import java.lang.Exception

class SelectGateWayViewModel : ViewModel() {

    private val gameConnector = GameConnector()
    var selectedGateWay = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun setCloud() {
        try {
            gameConnector.gateway = Cloud()
            selectedGateWay.value = Cloud.gateWayName
        } catch (e: Exception) {
            error.value = "Check your internet connection and try again"
        }

    }

    fun setSocket() {
        error.value = "Sockets to be implemented"
    }

    fun setBluetooth() {
        error.value = "Bluetooth to be implemented"
    }
}