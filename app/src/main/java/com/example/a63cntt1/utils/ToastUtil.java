package com.example.a63cntt1.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void successToast(Context ctx, String message){
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }
}
