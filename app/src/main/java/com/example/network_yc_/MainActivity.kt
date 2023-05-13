package com.example.network_yc_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.network_yc_.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var graphArrayList : ArrayList<Graph>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        graphArrayList = ArrayList()
        val jsonVide = "[]"
        val fileName = "graphs.json"
        val internalStorageDir = applicationContext.filesDir
        val file = File(internalStorageDir, fileName)
        if (!file.exists()) {
            try {
                file.createNewFile()
                file.writeText(jsonVide)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val jsonString = file.readText()

        if (jsonString.isBlank()) {
            print("Le Fichier est vide")
        } else {
            val gson = Gson()
            graphArrayList = gson.fromJson(jsonString, object : TypeToken<ArrayList<Graph>>() {}.type)
        }

        val add = binding.addNetwork
        add.setOnClickListener{
            val intent = Intent(this, AddNetwork::class.java)
            startActivity(intent)
        }

        binding.listNetwork.isClickable = true
        binding.listNetwork.adapter = MyAdapter(this,graphArrayList)
        binding.listNetwork.setOnItemClickListener { parent, view, position, id ->

            val name = graphArrayList[position].label
            val piece = graphArrayList[position].nbpiece

            val i = Intent(this, vueGraph::class.java)
            i.putExtra("label",name)
            i.putExtra("piece",piece)
            startActivity(i)
        }

    }



}