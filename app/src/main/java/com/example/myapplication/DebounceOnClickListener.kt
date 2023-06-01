package com.example.myapplication

import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.CompoundButton

class DebounceOnClickListener(
    private val interval: Long,
    private val listenerBlock: (View) -> Unit
) : View.OnClickListener {
    private var lastClickTime = 0L

    override fun onClick(v: View) {
        val time = System.currentTimeMillis()
        if (time - lastClickTime >= interval) {
            lastClickTime = time
            listenerBlock(v)
        }
    }
}


class DebounceCheckChangeListener(
    private val interval: Long,
    private val listenerBlock: (compoundButton: CompoundButton?, isChecked: Boolean) -> Unit
) : CompoundButton.OnCheckedChangeListener {
    //private var lastClickTime = 0L
    //private var lastTextChangedTime: Long = 0
    private var timer: CountDownTimer? = null
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        /*val time = System.currentTimeMillis()
        if (time - lastClickTime >= interval) {
            lastClickTime = time
            listenerBlock(buttonView, isChecked)
        } else {
            buttonView?.toggle()
        }*/
        /*if (SystemClock.elapsedRealtime() - lastTextChangedTime < interval) {
            buttonView?.toggle()
            return
        } else {
            listenerBlock(buttonView, isChecked)
        }
        lastTextChangedTime = SystemClock.elapsedRealtime()*/
        dispose()
        timer = object : CountDownTimer(interval, interval) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                listenerBlock(buttonView, isChecked)
            }
        }.start()
    }

    private fun dispose() {
        timer?.cancel()
    }

}

fun CompoundButton.setOnCheckChangeListener(
    debounceInterval: Long = 1000L,
    listenerBlock: (compoundButton: CompoundButton?, isChecked: Boolean) -> Unit
) =
    setOnCheckedChangeListener(DebounceCheckChangeListener(debounceInterval, listenerBlock))

fun View.setOnClickListener(debounceInterval: Long = 1000L, listenerBlock: (View) -> Unit) =
    setOnClickListener(DebounceOnClickListener(debounceInterval, listenerBlock))