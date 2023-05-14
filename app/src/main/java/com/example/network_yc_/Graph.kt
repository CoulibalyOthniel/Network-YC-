package com.example.network_yc_

import android.content.Context
import android.graphics.RectF
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException


class Graph {
    var label: String = ""
    var nbpiece: String = ""
    val objets = mutableListOf<Objet>()
    val connexions = mutableListOf<Connexion>()
    var bounds = RectF()



    fun reinitialiserReseau() {
        objets.clear()
        connexions.clear()
    }

    fun saveReseau(context: Context, graph: Graph) {
        val jsonString = "[]"
        val fileName = "graphs.json"
        val internalStorageDir = context.filesDir
        val file = File(internalStorageDir, fileName)
        if (!file.exists()) {
            try {
                file.createNewFile()
                file.writeText(jsonString)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        val gson = Gson()
        val jsonContent = file.readText()
        val graphList = gson.fromJson<ArrayList<Graph>>(jsonContent, object : TypeToken<ArrayList<Graph>>() {}.type)
            ?: ArrayList()
        graphList.add(graph)
        val json = gson.toJson(graphList)
        try {
            file.writeText(json)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }



    fun chargerGraph(context: Context, name: String): Graph? {
        val jsonString = File(context.filesDir,"graphs.json").readText()
        val graphs : ArrayList<Graph> = Gson().fromJson(jsonString, object : TypeToken<ArrayList<Graph>>() {}.type)
        return graphs.find { it.label == "${name}"  }
    }


    fun addGraphObject(obj: Objet) {
        objets.add(obj)
    }

    fun removeGraphObject(obj: Objet) {
        objets.remove(obj)
    }

    fun addGraphConnection(conn: Connexion) {
        connexions.add(conn)
    }

    fun removeGraphConnection(conn: Connexion) {
        connexions.remove(conn)
    }

}

