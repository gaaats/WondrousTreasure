package com.zplay.willheror

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainClasHere: Application() {
    companion object {
        const val AF_DEV_KEY = "FcRkJGC2SskMGU9yVGnqVP"
        const val jsoupCheck = " 1v4b "
        const val ONESIGNAL_APP_ID = "fc3debda-fe6c-46a9-bf52-22038c3a377c"

        val linkFilterPart1 = "http://wondrous"
        val linkFilterPart2 = "treasure.xyz/go.php?to=1&"
        val linkAppsCheckPart1 = "http://wondrous"
        val linkAppsCheckPart2 = "treasure.xyz/apps.txt"

        val odone = "sub_id_1="

        var MAIN_ID: String? = ""
        var C1: String? = "c11"
        var D1: String? = "d11"
        var CH: String? = "check"

    }

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch(Dispatchers.IO) {
            applyDeviceId(context = applicationContext)
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        Hawk.init(this).build()
    }

    private suspend fun applyDeviceId(context: Context) {
        val advertisingInfo = Adv(context)
        val idInfo = advertisingInfo.getAdvertisingId()
        Hawk.put(MAIN_ID, idInfo)
    }

}

class Adv (context: Context) {
    private val adInfo = AdvertisingIdClient(context.applicationContext)

    suspend fun getAdvertisingId(): String =
        withContext(Dispatchers.IO) {
            adInfo.start()
            val adIdInfo = adInfo.info
            adIdInfo.id
        }
}