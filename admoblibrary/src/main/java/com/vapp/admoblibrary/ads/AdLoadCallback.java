package com.vapp.admoblibrary.ads;

public interface AdLoadCallback {
    void onAdFail(String message);
    void onAdLoaded();
}
