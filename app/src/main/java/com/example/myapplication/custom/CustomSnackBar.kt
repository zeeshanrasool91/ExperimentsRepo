package com.example.myapplication.custom

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.myapplication.getColorCompat
import com.google.android.material.snackbar.Snackbar

class CustomSnackBar<DB : ViewDataBinding>(
    val activity: Activity,
    @LayoutRes val layoutRes: Int,
    val view: View? = null,
    private val length: Int = Snackbar.LENGTH_SHORT,
    private val backgroundColor: Int? = activity.getColorCompat(android.R.color.transparent),
    private val backgroundTint: Int? = activity.getColorCompat(android.R.color.transparent),
    private var onBindingInitiated: ((customSnackBar: CustomSnackBar<DB>, binding: DB) -> Unit)? = null,
    var cancelFun: ((isFinishingOrDestroyed: Boolean, closedManually: Boolean) -> Unit)? = null
) {


    private val finalView = view ?: activity.findViewById(android.R.id.content)
    private val inflater: LayoutInflater = LayoutInflater.from(finalView.context)
    private val snackBar = Snackbar.make(finalView, "", length)
    private val snackBarLayout = snackBar.view as ViewGroup
    private lateinit var binding: DB
    var bindData: ((DB) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) {
                invokeBindData()
            }
        }


    init {
        initBinding()
        initView()
        onBindingInitiated()
    }

    private fun invokeBindData() {
        bindData?.invoke(binding)
    }

    private fun onBindingInitiated() {
        onBindingInitiated?.invoke(this@CustomSnackBar, binding)
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate(inflater, layoutRes, null, false)
    }

    private fun initView() {
        with(snackBarLayout) {
            removeAllViews()
            addView(binding.root)
            setPadding(0, 0, 0, 0)
            elevation = 0f
            setBackgroundColor(
                backgroundColor ?: finalView.context.getColorCompat(android.R.color.transparent)
            )
            snackBar.setBackgroundTint(
                backgroundTint ?: finalView.context.getColorCompat(android.R.color.transparent)
            )
        }
    }

    fun show() {
        if (activity.isFinishing.not() || activity.isDestroyed.not()) {
            snackBar.show()
        }
    }

    fun dismiss() {
        if (snackBar.isShown) {
            snackBar.show()
        }
    }

    fun addCallBack() {
        if (activity.isFinishing.not() || activity.isDestroyed.not()) {
            snackBar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    if (event == DISMISS_EVENT_TIMEOUT) {
                        cancelFun?.invoke((activity.isFinishing || activity.isDestroyed), false)
                    }
                }

                override fun onShown(sb: Snackbar?) {
                    super.onShown(sb)
                }

            })
        }
    }

    fun getSnackBar(): Snackbar {
        return snackBar
    }

    fun getSnackBarLayout(): ViewGroup {
        return snackBarLayout
    }
}