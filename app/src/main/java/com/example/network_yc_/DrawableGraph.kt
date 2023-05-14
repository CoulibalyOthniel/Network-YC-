package com.example.network_yc_

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import androidx.core.content.ContextCompat


class DrawableGraph(val context: Context, var graph: Graph?) : android.graphics.drawable.Drawable() {


    private val objPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        style = Paint.Style.FILL
    }

    private val connPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.black)
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas) {

        for (obj in graph?.objets!!) {
            canvas.drawCircle(obj.x, obj.y, 50f, objPaint)
        }

        for (conn in graph?.connexions!!) {
            canvas.drawLine(
                conn.object1.x,
                conn.object1.y,
                conn.object2.x,
                conn.object2.y,
                connPaint
            )
        }
    }


    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        setBounds(bounds.left, bounds.top, bounds.right, bounds.bottom)
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }
}
