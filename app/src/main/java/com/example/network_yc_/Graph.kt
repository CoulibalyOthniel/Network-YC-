package com.example.network_yc_

import android.content.Context
import com.google.gson.Gson
import android.view.View
import com.google.gson.reflect.TypeToken
import java.io.File


class Graph {
    var label: String = ""
    var nbpiece: String = ""
    val objets = mutableListOf<Objet>()
    val connexions = mutableListOf<Connexion>()


    constructor(label: String, nbpiece: String) {
        this.label = label
        this.nbpiece = nbpiece
    }

    fun reinitialiserReseau() {
        objets.clear()
        connexions.clear()
    }

    fun saveReseau(context: Context, graph: Graph) {
        val file = File(context.filesDir, "graphs.json")
            val gson = Gson()
            val jsonContent = file.readText()
            val graphList = gson.fromJson<ArrayList<Graph>>(jsonContent, object : TypeToken<ArrayList<Graph>>() {}.type)
            graphList.add(graph)
    }

    fun load(name: String): Graph? {
        val gson = Gson()
        val jsonString = File("${name}.json").readText()
        return gson.fromJson(jsonString, Graph::class.java)
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

