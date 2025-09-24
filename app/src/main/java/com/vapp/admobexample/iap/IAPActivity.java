package com.vapp.admobexample.iap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vapp.admobexample.R;
import com.vapp.admoblibrary.iap.PurchaseCallback;
import com.vapp.admoblibrary.utils.Utils;
import com.vapp.admoblibrary.ads.AdmobUtils;
import com.vapp.admoblibrary.iap.PurchaseUtils;
import com.vapp.admoblibrary.iap.SkuDetailsModel;

public class IAPActivity extends AppCompatActivity {
    private TextView tvStatus;
    private Button btnPremium;
    private Button btnDetail;
    private Button btnRestore;

    private TextView tvStatusPurchases;
    private Button btnBuyPurchases;
    private Button btnDetailPurchases;
    private Button btnRestorePurchases;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iapp);

        tvStatus = findViewById(R.id.tv_status);
        btnPremium = findViewById(R.id.btn_premium);
        btnDetail = findViewById(R.id.btn_detail);
        btnRestore = findViewById(R.id.btn_restore);


        tvStatusPurchases = findViewById(R.id.tv_status_purchases);
        btnBuyPurchases = findViewById(R.id.btn_buy_purchases);
        btnDetailPurchases = findViewById(R.id.btn_detail_purchases);
        btnRestorePurchases = findViewById(R.id.btn_restore_purchases);


        btnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchaseUtils.getInstance().subscribeById(IAPActivity.this,getString(R.string.premium));
            }
        });
        btnDetail.setOnClickListener(v -> PurchaseUtils.getInstance().getDetailSubscribe(IAPActivity.this, getString(R.string.premium), new PurchaseCallback() {

           @Override
           public void onSkuDetailsResponse(SkuDetailsModel detailsModel) {
               Utils.getInstance().showMessenger(IAPActivity.this,detailsModel.getPriceValue().toString() + " " + detailsModel.getCurrency());

           }

            @Override
            public void onSkuDetailsError(String error) {

            }
       }));
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchaseUtils.getInstance().restoreSubscription(getString(R.string.premium),isPurchased -> {
                    if(isPurchased){
                        tvStatus.setText("Vip");
                    }
                    else{
                        tvStatus.setText("Free");
                    }
                });
            }
        });


        btnBuyPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchaseUtils.getInstance().purchaseById(IAPActivity.this,getString(R.string.product_id));
            }
        });
        btnDetailPurchases.setOnClickListener(v -> PurchaseUtils.getInstance().getDetailPurchase(IAPActivity.this, getString(R.string.product_id), new PurchaseCallback() {
            @Override
            public void onSkuDetailsResponse(SkuDetailsModel model) {
                Utils.getInstance().showMessenger(IAPActivity.this,model.getPriceValue().toString() + " " + model.getCurrency());

            }

            @Override
            public void onSkuDetailsError(String error) {
                Utils.getInstance().showMessenger(IAPActivity.this,error);
            }
        }));
        btnRestorePurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(() -> {
                    if (PurchaseUtils.getInstance().restorePurchase(getString(R.string.product_id))) {
                        tvStatus.setText("Vip");

                    }else {
                        tvStatus.setText("Free");
                    }
                }, 1000);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> {
            if (PurchaseUtils.getInstance().isSubscriptiond(getString(R.string.premium))) {
                tvStatus.setText("Vip");
                AdmobUtils.initAdmob(this, 10000,true,  false);

            }else {
                tvStatus.setText("Free");
                AdmobUtils.initAdmob(this, 10000,true,  true);

            }

            if (PurchaseUtils.getInstance().isPurchased(getString(R.string.product_id))) {
                tvStatusPurchases.setText("Buyed");
                AdmobUtils.initAdmob(this,10000, true,  false);

            }else {
                tvStatusPurchases.setText("not buy");
                AdmobUtils.initAdmob(this, 10000,true,  true);

            }


        }, 1000);
    }
}
