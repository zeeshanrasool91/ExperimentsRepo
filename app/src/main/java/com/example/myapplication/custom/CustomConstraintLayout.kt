package com.example.myapplication.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.OnSwipeTouchListener

class CustomConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val onSwipeTouchListener = object: OnSwipeTouchListener(context = context){
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                v.performClick()
                return super.onTouch(v, event)
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
            }

            override fun onSwipeTop() {
                super.onSwipeTop()
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
            }

        }
        setOnTouchListener(onSwipeTouchListener)
    }
}