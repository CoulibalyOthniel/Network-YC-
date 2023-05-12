package com.example.network_yc_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.network_yc_.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var graphArrayList : ArrayList<Graph>
    private lateinit var name: ArrayList<String>
    private lateinit var nbpiece: ArrayList<String>

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
        val fileDir = applicationContext.filesDir
        val gson = Gson() //

        fileDir.listFiles()?.forEach { file ->
            if (file.exists()) {
                val jsonString = file.readText()
                val graph = gson.fromJson(jsonString, Graph::class.java)
                name.add(graph.label)
                graphArrayList.add(graph)
            }
        }



        binding.listNetwork.isClickable = true
        binding.listNetwork.adapter = MyAdapter(this,graphArrayList)
        binding.listNetwork.setOnItemClickListener { parent, view, position, id ->

            val name = name[position]
            val nbpiece = nbpiece[position]

            val i = Intent(this, vueGraph::class.java)
            i.putExtra("label",name)
            i.putExtra("piece",nbpiece)
            startActivity(i)
        }

    }

}