package com.example.myapplication

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs


open class OnSwipeTouchListener(context: Context) : OnTouchListener {

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    private val gestureDetector: GestureDetector
    private var width = 0
    private var height = 0
    private var xValue = 0f
    private var yValue = 0f
    private var screenCenter = 0f

    init {
        gestureDetector = GestureDetector(context, GestureListener())
        height = DeviceUtils.getScreenHeight()
        width = DeviceUtils.getScreenWidth()
        screenCenter = ((width) * 50 / 100).toFloat()
    }
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        v.performClick()
        return gestureDetector.onTouchEvent(event)
    }



    private inner class GestureListener : SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                when {
                    abs(diffX) > abs(diffY) -> {
                        when {
                            abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD -> {
                                when {
                                    diffX > 0 -> {
                                        onSwipeRight()
                                    }

                                    else -> {
                                        onSwipeLeft()
                                    }
                                }
                                result = true
                            }
                        }
                    }
                    abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD -> {
                        when {
                            diffY > 0 -> {
                                onSwipeBottom()
                            }
                            else -> {
                                onSwipeTop()
                            }
                        }
                        result = true
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }

        override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
            event.let {
                xValue = event.x
                yValue = event.y
                when {
                    xValue <= screenCenter -> {
                        onSwipeRight()
                    }
                    xValue >= screenCenter -> {
                        onSwipeLeft()
                    }
                }
            }
            return super.onSingleTapConfirmed(event)
        }

    }

    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}
    open fun onSwipeTop() {}
    open fun onSwipeBottom() {}
}