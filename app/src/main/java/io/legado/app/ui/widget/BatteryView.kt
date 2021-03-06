package io.legado.app.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import io.legado.app.utils.dp

class BatteryView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private val batteryPaint = Paint()
    private val outFrame = Rect()
    private val polar = Rect()

    init {
        setPadding(4.dp, 0, 6.dp, 0)
        batteryPaint.strokeWidth = 1.dp.toFloat()
        batteryPaint.isAntiAlias = true
        batteryPaint.color = paint.color
    }

    fun setColor(@ColorInt color: Int) {
        setTextColor(color)
        batteryPaint.color = color
        invalidate()
    }

    @SuppressLint("SetTextI18n")
    fun setBattery(battery: Int) {
        text = "$battery"
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        outFrame.set(
            1.dp,
            layout.getLineTop(0) + 2.dp,
            width - 3.dp,
            layout.getLineBottom(0) - 2.dp
        )
        val dj = (outFrame.bottom - outFrame.top) / 3
        polar.set(
            outFrame.right,
            outFrame.top + dj,
            width - 1.dp,
            outFrame.bottom - dj
        )
        batteryPaint.style = Paint.Style.STROKE
        canvas.drawRect(outFrame, batteryPaint)
        batteryPaint.style = Paint.Style.FILL
        canvas.drawRect(polar, batteryPaint)
    }

}