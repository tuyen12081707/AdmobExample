package com.vapp.admoblibrary.ads;

import com.google.android.gms.ads.AdValue;

public interface AdCallbackNew {
    void onAdClosed();
    void onEventClickAdClosed();
    void onAdShowed();
    void onAdLoaded();
    void onAdFail();

}
