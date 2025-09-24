package com.ads.google.admobads.admobnative

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.nativead.NativeAd
import com.vapp.admoblibrary.R
import com.vapp.admoblibrary.ads.AdmobUtils
import com.vapp.admoblibrary.ads.NativeAdCallback
import com.vapp.admoblibrary.ads.admobnative.enumclass.GoogleENative

class GoogleNativeAdAdapter(private val mParam: Param) :
    GoogleRVAdapterWrapper(mParam.adapter as RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    var isFirst = false;
    private fun assertConfig() {
        if (mParam.gridLayoutManager != null) {
            val nCol = mParam.gridLayoutManager!!.spanCount
            require(mParam.adItemInterval % nCol == 0) {
                String.format(
                    "The adItemInterval (%d) is not divisible by number of columns in GridLayoutManager (%d)",
                    mParam.adItemInterval,
                    nCol
                )
            }
        }
    }

    private fun convertAdPosition2OrgPosition(position: Int): Int {
        return position - (position + 1) / (mParam.adItemInterval + 1)
    }

    override fun getItemCount(): Int {
        val realCount = super.getItemCount()
        return realCount + realCount / mParam.adItemInterval
    }

    override fun getItemViewType(position: Int): Int {
        return if (isAdPosition(position)) {
            TYPE_FB_NATIVE_ADS
        } else super.getItemViewType(convertAdPosition2OrgPosition(position))
    }

    private fun isAdPosition(position: Int): Boolean {
        return (position + 1) % (mParam.adItemInterval + 1) == 0
    }

    private fun onBindAdViewHolder(holder: RecyclerView.ViewHolder) {
        val adHolder = holder as AdViewHolder
        if (mParam.forceReloadAdOnBind || !adHolder.loaded) {

            var idAdmob = ""
            if (AdmobUtils.isTesting) {
                idAdmob = mParam.activity!!.getString(R.string.test_ads_admob_native_id);
            } else {
                idAdmob = mParam.idAdmob;
            }
            AdmobUtils.loadAndShowNativeAdsWithLayout(mParam.activity!!,
                idAdmob,
                holder.adFrame,
                mParam.layout, GoogleENative.UNIFIED_MEDIUM,object : NativeAdCallback {
                    override fun onLoadedAndGetNativeAd(ad: NativeAd?) {
                        TODO("Not yet implemented")
                    }

                    override fun onNativeAdLoaded() {}
                    override fun onAdFail(error: String?) {}
                    override fun onAdPaid(adValue: AdValue?, adUnitAds: String?) {
                        TODO("Not yet implemented")
                    }
                })
            adHolder.loaded = true

            adHolder.loaded = true
        }


    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (getItemViewType(position) == TYPE_FB_NATIVE_ADS) {
            onBindAdViewHolder(holder)
        } else {
            super.onBindViewHolder(holder, convertAdPosition2OrgPosition(position))
        }
    }

//    private fun onBindAdViewHolder(holder: RecyclerView.ViewHolder) {
//        val adHolder = holder as AdViewHolder
//        if (mParam.forceReloadAdOnBind || !adHolder.loaded) {
//           var idAdmob = ""
//            if (AdmodUtils.getInstance().isTesting){
//                idAdmob = mParam.activity!!.getString(R.string.test_ads_admob_native_id);
//            }else{
//                idAdmob = mParam.idAdmob;
//            }
//            AdmodUtils.getInstance().loadNativeAdsWithLayout(mParam.activity!!,
//                idAdmob,
//                holder.adFrame,
//                mParam.layout)
//            adHolder.loaded = true
//        }
//    }

//    override fun onBindViewHolder(
//        holder: RecyclerView.ViewHolder,
//        position: Int
//    ) {
//        if (getItemViewType(position) == TYPE_FB_NATIVE_ADS) {
//            isFirst = true;
//            onBindAdViewHolder(holder)
//        } else {
//
//            if (position <= mParam.adItemInterval) {
//                super.onBindViewHolder(holder,position)
//
//            } else {
//                super.onBindViewHolder(holder,position-1)
//
//            }
//
//        }
//    }

    private fun onCreateAdViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val adLayoutOutline = inflater
            .inflate(mParam.itemContainerLayoutRes, parent, false)
        val vg = adLayoutOutline.findViewById<ViewGroup>(mParam.itemContainerId)
        val adLayoutContent = inflater
            .inflate(R.layout.item_admob_native_ad, parent, false) as LinearLayout
        vg.addView(adLayoutContent)
        return AdViewHolder(
            adLayoutOutline
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_FB_NATIVE_ADS) {
            onCreateAdViewHolder(parent)
        } else super.onCreateViewHolder(parent, viewType)
    }

    private fun setSpanAds() {
        if (mParam.gridLayoutManager == null) {
            return
        }

        mParam.gridLayoutManager!!.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (isAdPosition(position)) {
                    mParam.gridSpanCount
                } else 1
            }
        }
    }

//     class Param() {
//        var gridLayoutManager: GridLayoutManager? = null
//        var adapter: RecyclerView.Adapter<*>? = null
//        var forceReloadAdOnBind = false
//        var gridSpanCount = 1
//        var adItemInterval = 0
//        var activity: Activity? = null
//        var layout: GoogleENative = GoogleENative.UNIFIED_MEDIUM
//
//        @LayoutRes
//        var itemContainerLayoutRes = 0
//
//        @IdRes
//        var itemContainerId = 0
//
//    }

    class Param(
        activity: Activity,
        mainAdapter: RecyclerView.Adapter<*>?,
        idAdmob: String,
        layoutCustom: Int,
        position: Int,
        itemContainerLayout: Int,
        itemContainer: Int
    ) {
        var gridLayoutManager: GridLayoutManager? = null
        var adapter = mainAdapter
        var forceReloadAdOnBind = false
        var gridSpanCount = 5
        var adItemInterval = position - 1
        var activity = activity
        var layout = layoutCustom
        var idAdmob = idAdmob

        @LayoutRes
        var itemContainerLayoutRes = itemContainerLayout

        @IdRes
        var itemContainerId = itemContainer

    }


    class Builder private constructor(private val mParam: Param) {
        fun adItemInterval(interval: Int): Builder {
            mParam.adItemInterval = interval
            return this
        }

        fun build(): GoogleNativeAdAdapter {
            return GoogleNativeAdAdapter(mParam)
        }

        fun enableSpanRow(layoutManager: GridLayoutManager?): Builder {
            mParam.gridLayoutManager = layoutManager
            return this
        }

        fun addLayout(layout: Int, id: Int): Builder {
            mParam.itemContainerLayoutRes = layout
            mParam.itemContainerId = id
            return this
        }

        fun setGridSpanCount(spanCount: Int): Builder {
            mParam.gridSpanCount = spanCount
            return this
        }

        fun forceReloadAdOnBind(forced: Boolean): Builder {
            mParam.forceReloadAdOnBind = forced
            return this
        }

        companion object {
            fun with(
                activity: Activity,
                idAdmob: String,
                wrapped: RecyclerView.Adapter<*>?,
                layout: Int,
                position: Int, itemContainerLayout: Int, itemContainer: Int
            ): Builder {
                val param = Param(
                    activity,
                    wrapped,
                    idAdmob,
                    layout,
                    position,
                    itemContainerLayout,
                    itemContainer
                )
                param.activity = activity
                param.adapter = wrapped
                param.idAdmob = idAdmob
                param.layout = layout
                param.adItemInterval =
                    position
                param.itemContainerLayoutRes = itemContainerLayout
                param.itemContainerId = itemContainer
                param.forceReloadAdOnBind = true
                return Builder(
                    param
                )
            }
        }

    }

    private class AdViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val adFrame = view.findViewById<FrameLayout>(R.id.ad_frame)
        var loaded: Boolean = false

    }

    companion object {
        private const val TYPE_FB_NATIVE_ADS = 900
        private const val DEFAULT_AD_ITEM_INTERVAL = 2
    }

    init {
        assertConfig()
        setSpanAds()
    }
}