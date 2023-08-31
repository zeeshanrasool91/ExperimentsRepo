package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

internal class UnderLineButtonView : MaterialButton {


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        applyAttributes(context, attrs)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        applyAttributes(context, attrs)
    }

    var mRect: Rect? = null
    var mPaint: Paint? = null
    var mColor = 0
    var density = 0f
    var mStrokeWidth = 0f
    var mLineTopMargin = 0f
    fun applyAttributes(context: Context, attrs: AttributeSet?) {
        //get screen density
        density = context.resources.displayMetrics.density
        // get custom properties
        val array = context.obtainStyledAttributes(attrs, R.styleable.UnderLineButtonView)
        mColor = array.getColor(R.styleable.UnderLineButtonView_underlineColor, -0x10000)
        mStrokeWidth =
            array.getDimension(R.styleable.UnderLineButtonView_underlineWidth, density * 2)
        mLineTopMargin =
            array.getDimension(R.styleable.UnderLineButtonView_underlineTopMargin, density * 2)
        setLineSpacing(mLineTopMargin, 1.5.toFloat())
        setPadding(left, top, right, bottom)
        array.recycle()
        mRect = Rect()
        mPaint = Paint()
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.color = mColor
        mPaint!!.strokeWidth = mStrokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        //Get the TextView to display how many lines there are
        val count = lineCount
        //Get the layout of the TextView
        val layout = layout
        var xStart: Float
        var xStop: Float
        var xDiff: Float
        var firstCharInLine: Int
        var lastCharInLine: Int
        for (i in 0 until count) {

            //getLineBounds gets the outer rectangle of this line, the top Y coordinate of this character is the top of the rect, and the bottom Y coordinate is the bottom of the rect
            val baseline = getLineBounds(i, mRect)
            // mRect.bottom+=mLineTopMargin;
            firstCharInLine = layout.getLineStart(i)
            lastCharInLine = layout.getLineEnd(i)

            //To get the left X coordinate of this character use layout.getPrimaryHorizontal
            //Get the right X coordinate of the character with layout.getSecondaryHorizontal
            xStart = layout.getPrimaryHorizontal(firstCharInLine)
            xDiff = layout.getPrimaryHorizontal(firstCharInLine + 1) - xStart
            xStop = layout.getPrimaryHorizontal(lastCharInLine - 1) + xDiff

            //canvas.drawLine(x_start,baseline+  mStrokeWidth,x_stop, baseline + mStrokeWidth, mPaint);
            canvas.drawLine(
                xStart,
                baseline + mLineTopMargin + mStrokeWidth,
                xStop,
                baseline + mLineTopMargin + mStrokeWidth,
                mPaint!!
            )
            //canvas.drawRect(x_start,baseline + mStrokeWidth,x_stop, baseline + mStrokeWidth, mPaint);
        }
        super.onDraw(canvas)
    }

    var underLineColor: Int
        get() = mColor
        set(mColor) {
            this.mColor = mColor
            invalidate()
        }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom + mLineTopMargin.toInt() + mStrokeWidth.toInt())
    }

    var underlineWidth: Float
        get() = mStrokeWidth
        set(mStrokeWidth) {
            this.mStrokeWidth = mStrokeWidth
            invalidate()
        }
}