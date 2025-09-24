package com.vapp.admoblibrary.ads.nativefullscreen

import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.nativead.NativeAd

interface NativeFullScreenCallBack {
    fun onLoaded(nativeAd: NativeAd)
    fun onLoadFailed()
    fun onPaidNative(adValue : AdValue, adUnitAds : String)
}