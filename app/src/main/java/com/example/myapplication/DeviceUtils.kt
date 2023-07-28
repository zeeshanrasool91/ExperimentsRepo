package com.example.myapplication

import android.content.res.Resources

object DeviceUtils {
    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels


    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels

}
