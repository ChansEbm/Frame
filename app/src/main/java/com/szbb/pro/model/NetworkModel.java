package com.szbb.pro.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.szbb.pro.AppKeyMap;
import com.szbb.pro.impl.OkHttpResponseListener;
import com.szbb.pro.tools.AppTools;
import com.szbb.pro.tools.LogTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChanZeeBm on 2015/12/26.
 */
public class NetworkModel<E> {
    private AppCompatActivity appCompatActivity;
    private Context context;
    private OkHttpResponseListener<E> tOkHttpResponseListener;
    private Map<String, String> params = new HashMap<>();

    private Map<String, String> fileMaps = new HashMap<>();//file params and each key
    // carry one filePath
    private List<List<String>> fileLists = new ArrayList<>();// file params and each key could be
    // carry multi filePaths

    public NetworkModel(@NonNull AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public NetworkModel(@NonNull Context context) {
        this.context = context;
        if (context instanceof AppCompatActivity) {
            this.appCompatActivity = (AppCompatActivity) context;
        }
    }

    public void setResultCallBack(OkHttpResponseListener<E> tOkHttpResponseListener) {
        this.tOkHttpResponseListener = tOkHttpResponseListener;
    }


    private boolean isNecessaryFieldEmpty(String... strings) {
        for (String string : strings) {
            LogTools.w(string);
            if (TextUtils.isEmpty(string)) {
                logError();
                return true;
            }
        }
        return false;
    }

    private boolean isNecessaryListEmpty(List<String>... lists) {
        for (List<String> list : lists) {
            if (list.isEmpty()) {
                logError();
                return true;
            }
        }
        return false;
    }

    private void logError() {
        LogTools.e("necessary field is empty");
    }


    private void clearAllParams() {
        params.clear();
    }

    /**
     * @return the user's auth
     */
    private String getAuth() {
        if (AppKeyMap.IS_DEBUG) {
            return "ADUPMwxrV2VRYgBnB2BQZVRrUWUFMwdnVWxQZgZlA2EBYA4zBjNTYFcyUmNXYgZlBTULPQBkAmADPlZnAjADZAAODzcMPFdh==";
        } else {
            if (AppKeyMap.isAuthEmpty()) {
                LogTools.e("Auth is null,plz check");
                return "";
            } else {
                return AppTools.getStringSharedPreferences(AppKeyMap.AUTH,
                        "ADAPMAw2V2RRMQAyB2VQYVRlUWUFYwcwVT1QYwZmA2MBPg5kBmFTMFdoUjBXbQY4BTcLOAA1AmcDOFZhAmADZAAODz4MPQ==");
            }
        }
    }
}
