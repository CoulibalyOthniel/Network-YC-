package com.example.network_yc_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class vueGraph : AppCompatActivity() {

    private lateinit var graphs : ArrayList<Graph>
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


    }


}