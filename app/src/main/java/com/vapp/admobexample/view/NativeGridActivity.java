package com.vapp.admobexample.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.google.admobads.admobnative.GoogleNativeAdAdapter;
import com.vapp.admobexample.R;
import com.vapp.admobexample.adapter.ClickListener;
import com.vapp.admobexample.adapter.ItemModel;
import com.vapp.admobexample.adapter.MainAdapter;
import com.vapp.admobexample.adapter.SectionedGridRecyclerViewAdapter;
import com.vapp.admobexample.adapter.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class NativeGridActivity extends AppCompatActivity {
    ArrayList<ItemModel> itemModel = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        mRecyclerView = findViewById(R.id.recyclerViewHome);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

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

        //Your RecyclerView.Adapter
        SimpleAdapter mAdapter = new SimpleAdapter(this, itemModel);

        //This is the code to provide a sectioned grid
        List<SectionedGridRecyclerViewAdapter.Section> sections =
                new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
        //add ad native to Sections %2

        for (int i = 1; i < itemModel.size(); i++) {
            if (i % 2 == 0) {
                sections.add(new SectionedGridRecyclerViewAdapter.Section(i));
            }
        }

        //Add your adapter to the sectionAdapter
        SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];

        SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                SectionedGridRecyclerViewAdapter(this, mAdapter,
                getString(R.string.test_ads_admob_native_id),
                R.layout.ad_template_medium, R.layout.layout_ad,
                R.id.id_ad,
                mRecyclerView);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        mRecyclerView.setAdapter(mSectionedAdapter);
    }
}