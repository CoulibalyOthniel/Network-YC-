package com.example.network_yc_

import com.google.gson.Gson
import android.view.View
import java.io.File

class Graph(var label: String, var nbpiece: String, var imageId: Int) {

    val objets = mutableListOf<Objet>()
    val connexions = mutableListOf<Connexion>()


    fun reinitialiserReseau() {
        objets.clear()
        connexions.clear()
    }

    fun saveReseau(graph: Graph) {
        val gson = Gson()
        val jsonString = gson.toJson(graph)
        File("${graph.label}.json").writeText(jsonString)
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
