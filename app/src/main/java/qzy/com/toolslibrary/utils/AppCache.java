package qzy.com.toolslibrary.utils;

import android.app.Application;
import android.content.Context;

import qzy.com.toolslibrary.service.PlayService;


public class AppCache {

    private Context mContext;
    private PlayService mPlayService;

    private AppCache() {
    }

    private static class SingletonHolder {
        private static AppCache sAppCache = new AppCache();
    }

    private static AppCache getInstance() {
        return SingletonHolder.sAppCache;
    }

    public static void initAppCache() {
        getContext();
    }

    public static void init(Application application) {
        getInstance().onInit(application);
    }

    private void onInit(Application application) {
        mContext = application.getApplicationContext();
    }

    public static Context getContext() {
        return getInstance().mContext;
    }

    public static PlayService getPlayService() {
        return getInstance().mPlayService;
    }

    public static void setPlayService(PlayService service) {
        getInstance().mPlayService = service;
    }


}
