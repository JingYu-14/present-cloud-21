package com.example.administrator.daoyunapplication.SignActivity;

import android.content.Context;

/**
 * Created by Administrator on 2020/4/18 0018.
 */

public class DensityUtil {
    private static float scale;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (pxValue / scale + 0.5f);
    }

}
