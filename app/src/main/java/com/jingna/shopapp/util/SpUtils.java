package com.jingna.shopapp.util;

import android.content.Context;
import com.vise.xsnow.cache.SpCache;

/**
 * Created by Administrator on 2018/10/26.
 */

public class SpUtils {

    private static SpCache spCache;
    public static String USER_ID = "user_id";

    public static void setUserId(Context context, String userid){
        spCache = new SpCache(context, "user_info");
        spCache.put(USER_ID, userid);
    }

    public static String getUserId(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(USER_ID, "0");
    }

}
