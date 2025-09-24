package com.vapp.admoblibrary.ads.model

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.interstitial.InterstitialAd

open class InterHolder(var ads: String) {
    var inter: InterstitialAd? = null
    val mutable: MutableLiveData<InterstitialAd> = MutableLiveData(null)
    var check = false
}