package com.example.network_yc_


import android.graphics.Color
import android.graphics.Path
import android.graphics.PointF
import android.view.GestureDetector
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    private val graph : Graph = Graph("","")
    private val drawableGraph = DrawableGraph(graph)

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        graph.bounds = RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawableGraph.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Lorsqu'on touche l'écran, on commence à dessiner une connexion
                // ou à déplacer un objet en fonction du mode de l'application
            }
            MotionEvent.ACTION_MOVE -> {
                // Lorsqu'on déplace le doigt, on met à jour la connexion temporaire
                // ou l'objet en train d'être déplacé
            }
            MotionEvent.ACTION_UP -> {
                // Lorsqu'on lâche le doigt, on crée la connexion ou on termine le déplacement
                // de l'objet en fonction du mode de l'application
            }
        }
        invalidate()
        return true
    }
}
