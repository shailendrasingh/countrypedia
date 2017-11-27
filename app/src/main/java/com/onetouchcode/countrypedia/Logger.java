package com.onetouchcode.countrypedia;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Logger {

    private static boolean isLogEnable = !BuildConfig.IS_PRODUCTION;

    public static void logInfo(String tag, String msg) {
        if (isLogEnable) {
            Log.v(tag, msg);
        }
    }

    public static void logError(String tag, String msg) {
        if (isLogEnable) {
            Log.e(tag, msg);
        }
    }

    public static void logError(String tag, String msg, Throwable throwable) {
        if (isLogEnable) {
            Log.e(tag, msg, throwable);
        }
    }

    public static void logException(String tag, Exception exception) {
        if (isLogEnable) {
            Log.w(tag, exception);
            exception.printStackTrace();
        }
    }

    public static void writeLogToSDCard(String logDetails) {
        if (isLogEnable) {
            try {
                File myFile = new File("/sdcard/smartfarmlog.txt");
                FileOutputStream fOut = null;
                if (!myFile.exists()) {
                    myFile.createNewFile();
                    fOut = new FileOutputStream(myFile);
                } else {
                    fOut = new FileOutputStream(myFile, Boolean.TRUE);
                }

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fOut));
                bw.write(logDetails);
                bw.newLine();
                bw.close();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
