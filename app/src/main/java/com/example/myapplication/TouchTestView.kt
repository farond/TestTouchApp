package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt

class TouchTestView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val gridRows = 7
    private val gridCols = 4
    private val touched = Array(gridRows) { BooleanArray(gridCols) }
    private val paint = Paint()

    init {
        paint.color = ContextCompat.getColor(context, R.color.orange)
        paint.style = Paint.Style.FILL
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val cellWidth = width / gridCols
        val cellHeight = height / gridRows
        for (row in 0 until gridRows) {
            for (col in 0 until gridCols) {
                if (touched[row][col]) {
                    canvas.drawRect(
                        (col * cellWidth).toFloat(), (row * cellHeight).toFloat(),
                        ((col + 1) * cellWidth).toFloat(), ((row + 1) * cellHeight).toFloat(),
                        paint
                    )
                }
            }
        }

        val linePaint = Paint().apply {
            color = Color.GRAY
            strokeWidth = 2f
        }

        for (i in 1 until gridCols) {
            val x = (i * cellWidth).toFloat()
            canvas.drawLine(x, 0f, x, height.toFloat(), linePaint)
        }

        for (i in 1 until gridRows) {
            val y = (i * cellHeight).toFloat()
            canvas.drawLine(0f, y, width.toFloat(), y, linePaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val col = (event.x / (width / gridCols)).toInt()
        val row = (event.y / (height / gridRows)).toInt()
        if (row in 0 until gridRows && col in 0 until gridCols) {
            touched[row][col] = true
            invalidate()
            if (allTouched()) {
                (context as? TestTouchActivity)?.findViewById<Button>(R.id.btn_passed)?.visibility = VISIBLE
            }
        }
        return true
    }

    private fun allTouched(): Boolean {
        return touched.all { row -> row.all { it } }
    }
}