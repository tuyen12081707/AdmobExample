package com.vapp.admoblibrary.ads.model

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.nativead.NativeAd

open class NativeHolder(var ads: String){
    var nativeAd : NativeAd?= null
    var isLoad = false
    var native_mutable: MutableLiveData<NativeAd> = MutableLiveData()
}