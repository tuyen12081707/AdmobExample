package com.vapp.admoblibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vapp.admoblibrary.R;
import com.vapp.admoblibrary.ads.model.AdUnitListModel;
import com.vapp.admoblibrary.utils.SweetAlert.SweetAlertDialog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.TELEPHONY_SERVICE;


public class Utils {
    public SweetAlertDialog pDialog;
    private static volatile Utils INSTANCE;

    public static synchronized Utils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Utils();
        }
        return INSTANCE;
    }

    public static List<AdUnitListModel> adUnitLists = new ArrayList<>();
    String countryCode = "";


    public void showProgress(Context context, String title, String hexcolor) {
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(hexcolor));
        pDialog.setTitleText(title);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void dismissProgress(Context context, String title, String hexcolor) {
        pDialog.dismissWithAnimation();
    }

    public void showDialogTitle(Context context, String title, String content, String lableButton, DialogType dialogType, boolean isEnableCancel, String lableCancel, DialogCallback dialogCallback) {
        int type = 0;
        switch (dialogType) {
            case NORMAL_TYPE:
                type = 0;
                break;
            case ERROR_TYPE:
                type = 1;
                break;
            case SUCCESS_TYPE:
                type = 2;
                break;
            case WARNING_TYPE:
                type = 3;
                break;
            case PROGRESS_TYPE:
                type = 4;
            default:
                type = 0;
        }

        if (isEnableCancel) {
            new SweetAlertDialog(context, type)
                    .setTitleText(title)
                    .setContentText(content)
                    .setConfirmText(lableButton)
                    .setCancelText(lableCancel)
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            dialogCallback.cancel();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            dialogCallback.onClosed();
                        }
                    })
                    .show();
        } else {
            new SweetAlertDialog(context, type)
                    .setTitleText(title)
                    .setContentText(content)
                    .setConfirmText(lableButton)
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            dialogCallback.cancel();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            dialogCallback.onClosed();
                        }
                    })
                    .show();
        }


    }


    public boolean checkCountries(Context context, AdUnitListModel adUnitList) {
        countryCode = getDeviceCountryCode(context);
        boolean isShowAds = false;
        if (adUnitList.getCountries() == null) {
            isShowAds = true;
            return isShowAds;
        }
        List<String> countries = adUnitList.getCountries();
        if (countries.size() > 0) {
            for (String item : countries) {
                if (item.matches("(?i)(" + countryCode + ").*")) {
                    isShowAds = true;
                }
            }
        } else {
            isShowAds = true;
        }
        return isShowAds;
    }

    public String getCurrentCountry(Context context) {
        String countryCode = "";

        TelephonyManager ts = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        countryCode = ts.getNetworkCountryIso().toUpperCase();

        if (countryCode.length() < 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                countryCode = LocaleList.getDefault().get(0).getCountry();
                return countryCode;
            } else {
                countryCode = Locale.getDefault().getCountry();
                return countryCode;
            }
        }
        return countryCode;
    }

    public String getDeviceCountryCode(Context context) {
        String countryCode;

        // try to get country code from TelephonyManager service
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            // query first getSimCountryIso()
            countryCode = tm.getSimCountryIso();
            if (countryCode != null && countryCode.length() == 2)
                return countryCode.toLowerCase();

            if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                // special case for CDMA Devices
                countryCode = getCDMACountryIso();
            } else {
                // for 3G devices (with SIM) query getNetworkCountryIso()
                countryCode = tm.getNetworkCountryIso();
            }

            if (countryCode != null && countryCode.length() == 2)
                return countryCode.toLowerCase();
        }

        // if network country not available (tablets maybe), get country code from Locale class
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            countryCode = context.getResources().getConfiguration().getLocales().get(0).getCountry();
        } else {
            countryCode = context.getResources().getConfiguration().locale.getCountry();
        }

        if (countryCode != null && countryCode.length() == 2)
            return countryCode.toLowerCase();
        // general fallback to "us"
        return "us";
    }

    @SuppressLint("PrivateApi")
    private static String getCDMACountryIso() {
        try {
            // try to get country code from SystemProperties private class
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            Method get = systemProperties.getMethod("get", String.class);

            // get homeOperator that contain MCC + MNC
            String homeOperator = ((String) get.invoke(systemProperties,
                    "ro.cdma.home.operator.numeric"));

            // first 3 chars (MCC) from homeOperator represents the country code
            int mcc = Integer.parseInt(homeOperator.substring(0, 3));

            // mapping just countries that actually use CDMA networks
            switch (mcc) {
                case 330:
                    return "PR";
                case 310:
                    return "US";
                case 311:
                    return "US";
                case 312:
                    return "US";
                case 316:
                    return "US";
                case 283:
                    return "AM";
                case 460:
                    return "CN";
                case 455:
                    return "MO";
                case 414:
                    return "MM";
                case 619:
                    return "SL";
                case 450:
                    return "KR";
                case 634:
                    return "SD";
                case 434:
                    return "UZ";
                case 232:
                    return "AT";
                case 204:
                    return "NL";
                case 262:
                    return "DE";
                case 247:
                    return "LV";
                case 255:
                    return "UA";
            }
        } catch (ClassNotFoundException ignored) {
        } catch (NoSuchMethodException ignored) {
        } catch (IllegalAccessException ignored) {
        } catch (InvocationTargetException ignored) {
        } catch (NullPointerException ignored) {
        }

        return null;
    }

    public boolean showAdForCountry(Context context, AdUnitListModel adModel) {
        if (adModel.getCountries() != null) {
            return Utils.getInstance().checkCountries(context, adModel);
        }
        return true;
    }

    public void showMessenger(Context context, String content, int time) {
        if (time == 0) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();

        }
    }

    public void showMessenger(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }



    public void addActivity(Context context, Class activity) {
        Intent i = new Intent(context, activity);
        context.startActivity(i);
    }

    public void replaceActivity(Context context, Class activity) {
        Intent i = new Intent(context, activity);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    public void addFragment(AppCompatActivity context, Fragment fragment, int contentFrame, boolean addToBackStack) {
        FragmentTransaction transaction = context.getSupportFragmentManager()
                .beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        } else {
            transaction.addToBackStack(fragment.toString());
        }
        if (fragment.getTag() == null) {
            transaction.replace(contentFrame, fragment, fragment.toString());
        } else {
            transaction.replace(contentFrame, fragment, fragment.getTag());
        }
        transaction.commit();
    }

    public void replaceFragment(FragmentManager fm, Fragment fragment, int contentFrame, boolean addToBackStack) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        } else {
            transaction.addToBackStack(fragment.toString());
        }
        if (fragment.getTag() == null) {
            transaction.replace(contentFrame, fragment, fragment.toString());
        } else {
            transaction.replace(contentFrame, fragment, fragment.getTag());
        }
        transaction.commit();
    }

    public AdUnitListModel getAdUnit(String id) {
        if (adUnitLists.size() > 0) {
            for (AdUnitListModel adUnitList1 : adUnitLists) {
                if (adUnitList1.getAdUnitName().equals(id)) {
                    return adUnitList1;
                }
            }
        }
        return null;
    }

    public AdUnitListModel getDefaultAdUnit(String id) {
        AdUnitListModel adUnitList = new AdUnitListModel();
        adUnitList.setIsShow(true);
        adUnitList.setIsAdmob(true);
        adUnitList.setAdmobId(id);
        return adUnitList;
    }

    public AdUnitListModel getAdUnitByName(String name, String defaulID) {
        if (getAdUnit(name) != null) {
            return getAdUnit(name);
        } else {
            return getDefaultAdUnit(defaulID);
        }
    }


}
