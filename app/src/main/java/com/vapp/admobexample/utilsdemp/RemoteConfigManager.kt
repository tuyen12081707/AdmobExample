package com.vapp.admobexample.utilsdemp

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.vapp.admobexample.R
import com.vapp.admoblibrary.ads.remote.BannerPlugin

internal object RemoteConfigManager {

    private val gson by lazy { Gson() }

    fun initRemoteConfig(listener: OnCompleteListener<Boolean>) {
        val mFirebaseRemoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings: FirebaseRemoteConfigSettings = FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(3600).build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(listener)
    }

    fun getBannerConfig(key: String): BannerPlugin.BannerConfig? {
        return getConfig<BannerPlugin.BannerConfig>(key)
    }

    private inline fun <reified T> getConfig(configName: String): T? {
        return try {
            val data = FirebaseRemoteConfig.getInstance().getString(configName)
            gson.fromJson<T>(data, object : TypeToken<T>() {}.type)
        } catch (ignored: Throwable) {
            null
        }
    }

}