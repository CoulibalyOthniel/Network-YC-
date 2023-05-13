package com.example.network_yc_

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.example.network_yc_.DrawableGraph
import android.os.Bundle
import android.widget.ImageView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class vueGraph : AppCompatActivity() {
    
    private lateinit var graphs : ArrayList<Graph>
    @SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vue_graph)


        val name : String? = intent.getStringExtra("label")
        val nbpiece : String? = intent.getStringExtra("piece")

        val actionBar = supportActionBar
        actionBar?.title = "Reseau ${name}"

        val jsonString = File(applicationContext.filesDir,"graphs.json").readText()
        graphs = ArrayList()
        graphs = Gson().fromJson(jsonString, object : TypeToken<ArrayList<Graph>>() {}.type)

        val graphLoad : Graph? = graphs.find {it.label == "${name}"}
        val imageView = findViewById<ImageView>(R.id.imageReseau)
        val resourceId = resources.getIdentifier(nbpiece, "drawable", packageName)
        val myDrawable = resources.getDrawable(resourceId, null)
        imageView.setImageDrawable(myDrawable)

    }


}