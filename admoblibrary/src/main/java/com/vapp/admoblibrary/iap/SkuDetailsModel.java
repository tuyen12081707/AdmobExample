package com.vapp.admoblibrary.iap;

public class SkuDetailsModel {

    public  String productId;

    public  String title;

    public  String description;

    public  boolean isSubscription;

    public  String currency;

    public  Double priceValue;

    public  String subscriptionPeriod;

    public  String subscriptionFreeTrialPeriod;

    public  boolean haveTrialPeriod;

    public  double introductoryPriceValue;

    public  String introductoryPricePeriod;

    public  boolean haveIntroductoryPeriod;

    public  int introductoryPriceCycles;

    public SkuDetailsModel(String productId, String title, String description, boolean isSubscription, String currency, Double priceValue, String subscriptionPeriod, String subscriptionFreeTrialPeriod, boolean haveTrialPeriod, double introductoryPriceValue, String introductoryPricePeriod, boolean haveIntroductoryPeriod, int introductoryPriceCycles) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.isSubscription = isSubscription;
        this.currency = currency;
        this.priceValue = priceValue;
        this.subscriptionPeriod = subscriptionPeriod;
        this.subscriptionFreeTrialPeriod = subscriptionFreeTrialPeriod;
        this.haveTrialPeriod = haveTrialPeriod;
        this.introductoryPriceValue = introductoryPriceValue;
        this.introductoryPricePeriod = introductoryPricePeriod;
        this.haveIntroductoryPeriod = haveIntroductoryPeriod;
        this.introductoryPriceCycles = introductoryPriceCycles;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSubscription() {
        return isSubscription;
    }

    public void setSubscription(boolean subscription) {
        isSubscription = subscription;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Double priceValue) {
        this.priceValue = priceValue;
    }

    public String getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(String subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public String getSubscriptionFreeTrialPeriod() {
        return subscriptionFreeTrialPeriod;
    }

    public void setSubscriptionFreeTrialPeriod(String subscriptionFreeTrialPeriod) {
        this.subscriptionFreeTrialPeriod = subscriptionFreeTrialPeriod;
    }

    public boolean isHaveTrialPeriod() {
        return haveTrialPeriod;
    }

    public void setHaveTrialPeriod(boolean haveTrialPeriod) {
        this.haveTrialPeriod = haveTrialPeriod;
    }

    public double getIntroductoryPriceValue() {
        return introductoryPriceValue;
    }

    public void setIntroductoryPriceValue(double introductoryPriceValue) {
        this.introductoryPriceValue = introductoryPriceValue;
    }

    public String getIntroductoryPricePeriod() {
        return introductoryPricePeriod;
    }

    public void setIntroductoryPricePeriod(String introductoryPricePeriod) {
        this.introductoryPricePeriod = introductoryPricePeriod;
    }

    public boolean isHaveIntroductoryPeriod() {
        return haveIntroductoryPeriod;
    }

    public void setHaveIntroductoryPeriod(boolean haveIntroductoryPeriod) {
        this.haveIntroductoryPeriod = haveIntroductoryPeriod;
    }

    public int getIntroductoryPriceCycles() {
        return introductoryPriceCycles;
    }

    public void setIntroductoryPriceCycles(int introductoryPriceCycles) {
        this.introductoryPriceCycles = introductoryPriceCycles;
    }
}
