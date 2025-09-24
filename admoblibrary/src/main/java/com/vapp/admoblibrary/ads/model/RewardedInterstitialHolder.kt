package com.vapp.admoblibrary.ads.model

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd

open class RewardedInterstitialHolder(var ads: String) {
    var inter: RewardedInterstitialAd? = null
    val mutable: MutableLiveData<RewardedInterstitialAd> = MutableLiveData(null)
    var isLoading = false
}