package com.vapp.admoblibrary.ads;

import com.google.android.gms.ads.AdValue;

public interface RewardAdCallback {
    void onAdClosed();
    void onAdShowed();
    void onAdFail(String message);
    void onEarned();
    void onPaid(AdValue adValue, String adUnitAds);

}
