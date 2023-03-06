package com.example.myapplication

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.GenericToastPopupBindingBinding
import com.facebook.stetho.Stetho
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyApplication : Application(), Application.ActivityLifecycleCallbacks {
    private var activityReference: Activity? = null

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        registerActivityLifecycleCallbacks(this)
    }

    fun showCustomToast(toast: String) {
        try {
            activityReference?.window?.decorView?.let {
                val popupWindow = PopupWindow(it.width - 200, ViewGroup.LayoutParams.WRAP_CONTENT)
                val binding = GenericToastPopupBindingBinding.inflate(LayoutInflater.from(this))
                binding.toastMessage.text = toast
                popupWindow.contentView = binding.root
                binding.close.setOnClickListener {
                    popupWindow.dismiss()
                }
                popupWindow.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_rectangle_white
                    )
                )
                popupWindow.animationStyle = android.R.style.Animation_Toast
                popupWindow.showAtLocation(it, Gravity.BOTTOM, 0, 500)
                MainScope().launch {
                    delay(5000)
                    popupWindow.dismiss()
                }
            }

        } catch (e: Exception) {
            Log.d("TAG", "showCustomToast: ")
        }

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityReference = activity
    }

    override fun onActivityStarted(activity: Activity) {
        activityReference = activity
    }

    override fun onActivityResumed(activity: Activity) {
        activityReference = activity
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }
}