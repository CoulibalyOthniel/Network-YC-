package com.example.network_yc_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.network_yc_.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.File

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var graphArrayList : ArrayList<Graph>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val add = binding.addNetwork
        add.setOnClickListener{
            val intent = Intent(this, addNetwork::class.java)
            startActivity(intent)
        }

        graphArrayList = ArrayList()

        val gson = Gson()
        val directory = File(".")
        val graphs = directory.listFiles { file -> file.isFile && file.name.endsWith(".json") }
        for (graph in graphs){
            val rs = gson.fromJson(graph.readText(), Graph::class.java)
            graphArrayList.add(rs)
        }

    }

}