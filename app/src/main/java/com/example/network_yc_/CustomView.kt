package com.example.network_yc_

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Canvas
import android.graphics.Color
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
    private var selectedObject: Objet? = null
    private var connectionStart: Objet? = null
    private var connectionEnd: Objet? = null

    @SuppressLint("DrawAllocation", "SuspiciousIndentation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyName", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("Name", "")
        val jsonString = File(context.filesDir,"graphs.json")
            jsonString.readText()
            val graphs : ArrayList<Graph> = Gson().fromJson(jsonString, object : TypeToken<ArrayList<Graph>>() {}.type)
            graph = graphs.find { it.label == "${name}"  }!!
            graph.bounds = RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawableGraph.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                val x = event.x
                val y = event.y

                selectedObject = graph.findObjectAt(x, y,graph)

                selectedObject?.let {
                    connectionStart = it
                }
            }
            MotionEvent.ACTION_MOVE -> {

                val x = event.x
                val y = event.y

                selectedObject?.let {
                    it.position.centerX()
                    it.position.centerY()
                }
            }
            MotionEvent.ACTION_UP -> {

                val x = event.x
                val y = event.y

                val touchedObject = graph.findObjectAt(x, y,graph)
                connectionEnd = touchedObject
                if (connectionStart != null && connectionEnd != null && connectionStart != connectionEnd) {
                    graph.addGraphConnection(Connexion("Line", Color.BLACK,
                        connectionStart!!, connectionEnd!!))
                }

                selectedObject = null
                connectionStart = null
                connectionEnd = null
            }
        }
        invalidate()
        return true
    }
}
