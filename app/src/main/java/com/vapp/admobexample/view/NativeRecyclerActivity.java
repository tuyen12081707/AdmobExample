package com.vapp.admobexample.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.google.admobads.admobnative.GoogleNativeAdAdapter;
import com.vapp.admobexample.R;
import com.vapp.admobexample.adapter.ClickListener;
import com.vapp.admobexample.adapter.ItemModel;
import com.vapp.admobexample.adapter.MainAdapter;

import java.util.ArrayList;

public class NativeRecyclerActivity extends AppCompatActivity implements ClickListener {
    ArrayList<ItemModel> itemModel = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        recyclerView = findViewById(R.id.recyclerViewHome);

        itemModel.add(new ItemModel("Create item 1"));
        itemModel.add(new ItemModel("Create item 2"));
        itemModel.add(new ItemModel("Create item 3"));
        itemModel.add(new ItemModel("Create item 4"));
        itemModel.add(new ItemModel("Create item 5"));
        itemModel.add(new ItemModel("Create item 6"));
        itemModel.add(new ItemModel("Create item 7"));
        itemModel.add(new ItemModel("Create item 8"));
        itemModel.add(new ItemModel("Create item 9"));
        itemModel.add(new ItemModel("Create item 10"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter mainAdapter = new MainAdapter(itemModel, this::onNavigate);
        GoogleNativeAdAdapter googleNativeAdAdapter = new GoogleNativeAdAdapter(
                new GoogleNativeAdAdapter.Param(
                        NativeRecyclerActivity.this,
                        mainAdapter,
                        getString(R.string.test_ads_admob_native_id),
                        R.layout.ad_template_medium, //
                        4,
                        R.layout.layout_ad,
                        R.id.id_ad
                ));
        recyclerView.setAdapter(googleNativeAdAdapter);
        //position = position adapter not position recyclerView
        //if adapter.notifydatasetchanged = crash -> init adapter again
    }

    @Override
    public void onNavigate(int position) {

    }
}
