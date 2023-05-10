package com.example.network_yc_

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.textfield.TextInputLayout

class addNetwork : AppCompatActivity() {

    private lateinit var graph: Graph
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_network)

        val actionBar = supportActionBar

        actionBar?.title = "Creation d'un reseau"

        val nomReseau = findViewById<EditText>(R.id.nameNetwork)
        val nbpiece = findViewById<EditText>(R.id.numPiece)
        val imageName : View = findViewById(R.id.addImage)
        val confirmReseau : View = findViewById(R.id.confirm)



        val nomValue = nomReseau.text.toString()
        val nbvalue = nbpiece.text.toString()
        val imageId = imageName.id


        confirmReseau.setOnClickListener{
           graph = Graph(nomValue,nbvalue,imageId)
            graph.saveReseau(graph)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }




}
