package com.example.quan_ly_quy_lop.logUtil;

import android.util.Log;

public class LogUtil {
    public static final boolean WRITE_LOG = true;

    public static void LogD(String tag, String msg) {
        if(WRITE_LOG) {
            Log.d(tag, msg);
        }
    }

    public static void LogE(String tag, String msg) {
        if(WRITE_LOG) {
            Log.e(tag, msg);
        }
    }

    public static void LogI(String tag, String msg) {
        if(WRITE_LOG) {
            Log.i(tag, msg);
        }
    }

    public static void LogW(String tag, String msg) {
        if(WRITE_LOG) {
            Log.w(tag, msg);
        }
    }
}