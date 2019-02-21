package com.jingna.shopapp.app;

import android.app.Activity;
import android.app.Application;

import com.jingna.shopapp.util.Const;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/13.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> mList = new LinkedList<Activity>();

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(Const.BASE_URL);
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}
