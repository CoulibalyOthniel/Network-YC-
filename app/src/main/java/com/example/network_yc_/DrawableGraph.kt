package com.example.network_yc_

import android.graphics.Canvas
import android.graphics.ColorFilter


class DrawableGraph(val graph: Graph) : android.graphics.drawable.Drawable() {
    override fun draw(canvas: Canvas) {
        for (obj in graph.objets){

        }
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("Not yet implemented")
    }

    override fun getOpacity(): Int {
        TODO("Not yet implemented")
    }
}