package com.example.network_yc_

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class CustomView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context.applicationContext, attrs) {

    private var graph : Graph = Graph()
    private val drawableGraph = DrawableGraph(graph)

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyName", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("Name", "")
        val jsonString = File(context.filesDir,"graphs.json")
        if (jsonString.exists()) {
            jsonString.readText()
            val graphs : ArrayList<Graph> = Gson().fromJson(jsonString, object : TypeToken<ArrayList<Graph>>() {}.type)
            graph = graphs.find { it.label == "${name}"  }!!
            graph.bounds = RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
        }

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
