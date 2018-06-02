package br.com.bpp.bppmobiletest.iii_utils;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Utils {

    public static String convertToBase64(String strReceived) {
        byte[] data = strReceived.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeToString(data, Base64.DEFAULT).trim();
    }
}


