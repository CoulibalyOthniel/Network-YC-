package com.example.network_yc_

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable

class DrawableGraph(val graph: Graph) : Drawable() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun draw(canvas: Canvas) {

        for (objet in graph.objets) {
            paint.color = objet.color
            canvas.drawRoundRect(objet.position, 16f, 16f, paint)
            canvas.drawText(objet.name,
                objet.position.centerX().toFloat(), objet.position.centerY() + paint.textSize / 3, paint)
        }
        for (conn in graph.connexions) {
            paint.color = conn.color
            paint.strokeWidth = conn.thickness.toFloat()
            canvas.drawLine(conn.object1.position.centerX(), conn.object1.position.centerY(),
                conn.object2.position.centerX(), conn.object2.position.centerY(), paint)
            canvas.drawText(conn.label, (conn.object1.position.centerX() + conn.object2.position.centerX()) / 2f,
                (conn.object1.position.centerY() + conn.object2.position.centerY()) / 2f + paint.textSize / 3, paint)
        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}

