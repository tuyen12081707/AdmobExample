package com.vapp.admoblibrary.ads.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdUnitListModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("countries")
    @Expose
    private List<String> countries;
    @SerializedName("adUnitName")
    @Expose
    private String adUnitName;
    @SerializedName("admobId")
    @Expose
    private String admobId;
    @SerializedName("appodealId")
    @Expose
    private String appodealId;
    @SerializedName("isAdmob")
    @Expose
    private Boolean isAdmob;
    @SerializedName("isShow")
    @Expose
    private Boolean isShow;
    @SerializedName("adUnitType")
    @Expose
    private Integer adUnitType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }


    public String getAdUnitName() {
        return adUnitName;
    }

    public void setAdUnitName(String adUnitName) {
        this.adUnitName = adUnitName;
    }

    public String getAdmobId() {
        return admobId;
    }

    public void setAdmobId(String admobId) {
        this.admobId = admobId;
    }

    public String getAppodealId() {
        return appodealId;
    }

    public void setAppodealId(String appodealId) {
        this.appodealId = appodealId;
    }

    public Boolean getIsAdmob() {
        return isAdmob;
    }

    public void setIsAdmob(Boolean isAdmob) {
        this.isAdmob = isAdmob;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Integer getAdUnitType() {
        return adUnitType;
    }

    public void setAdUnitType(Integer adUnitType) {
        this.adUnitType = adUnitType;
    }

}