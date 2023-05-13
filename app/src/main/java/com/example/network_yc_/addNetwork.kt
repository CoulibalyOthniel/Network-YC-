package com.example.network_yc_

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner


class AddNetwork : AppCompatActivity() {

    private lateinit var graphOne: Graph
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_network)

        val actionBar = supportActionBar
        actionBar?.title = "Creation d'un reseau"

        val nomReseau = findViewById<EditText>(R.id.nomNetwork)
        val nbpiece = findViewById<Spinner>(R.id.nbpiece)
        val confirmReseau : View = findViewById(R.id.confirm)



        confirmReseau.setOnClickListener{
            val nomValue = nomReseau.text.toString()
            val nbvalue = nbpiece.selectedItem.toString()
            graphOne = Graph(nomValue,nbvalue)
            graphOne.saveReseau(this,graphOne)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }




}
