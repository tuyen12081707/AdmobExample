package com.vapp.admoblibrary.ads.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdsConfigModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("admobLimitTime")
    @Expose
    private Integer admobLimitTime;
    @SerializedName("appodealLimitTime")
    @Expose
    private Integer appodealLimitTime;
    @SerializedName("adUnitList")
    @Expose
    private List<AdUnitListModel> adUnitList = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getAdmobLimitTime() {
        return admobLimitTime;
    }

    public void setAdmobLimitTime(Integer admobLimitTime) {
        this.admobLimitTime = admobLimitTime;
    }

    public Integer getAppodealLimitTime() {
        return appodealLimitTime;
    }

    public void setAppodealLimitTime(Integer appodealLimitTime) {
        this.appodealLimitTime = appodealLimitTime;
    }

    public List<AdUnitListModel> getAdUnitList() {
        return adUnitList;
    }

    public void setAdUnitList(List<AdUnitListModel> adUnitList) {
        this.adUnitList = adUnitList;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
