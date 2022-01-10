package com.newcapec.lib.melexis;

import android.content.Context;

public class NcJni {
    private final String TAG = "DataProcess";

    static {
        System.loadLibrary("melexis_data_proc");
    }

    private NcJni(Context context) {
    }

    public static native boolean InitParameters(byte[] initData);
    public static native boolean Calculate(byte[] orgData, float emissivity, float tr, float[] resultData);
    public static native boolean CalculateInteger(byte[] orgData, float emissivity, float tr, int[] resultData);
}
