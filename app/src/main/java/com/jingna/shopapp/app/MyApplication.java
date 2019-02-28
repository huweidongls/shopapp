package com.jingna.shopapp.app;

import android.app.Activity;
import android.app.Application;

import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.EditPhoneNum2TimeCount;
import com.jingna.shopapp.util.EditPhoneNumTimeCount;
import com.jingna.shopapp.util.EditPwdTimeCount;
import com.jingna.shopapp.util.FTPTimeCount;
import com.jingna.shopapp.util.ForgotTimeCount;
import com.jingna.shopapp.util.SMSCodeTimeCount;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/13.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> mList = new LinkedList<Activity>();
    // 修改密码获取验证码倒计时
    public static FTPTimeCount ftptimecount;
    public static SMSCodeTimeCount smsCodeTimeCount;
    public static ForgotTimeCount forgotTimeCount;
    public static EditPwdTimeCount editPwdTimeCount;
    public static EditPhoneNumTimeCount editPhoneNumTimeCount;
    public static EditPhoneNum2TimeCount editPhoneNum2TimeCount;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(Const.BASE_URL);
        ftptimecount = new FTPTimeCount(60000, 1000);
        smsCodeTimeCount = new SMSCodeTimeCount(60000, 1000);
        forgotTimeCount = new ForgotTimeCount(60000, 1000);
        editPwdTimeCount = new EditPwdTimeCount(60000, 1000);
        editPhoneNumTimeCount = new EditPhoneNumTimeCount(60000, 1000);
        editPhoneNum2TimeCount = new EditPhoneNum2TimeCount(60000, 1000);
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
