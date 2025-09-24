package com.vapp.admoblibrary.iap;

public interface PurchaseCallback {
    void onSkuDetailsResponse(SkuDetailsModel model);
    void onSkuDetailsError(String error);
}
