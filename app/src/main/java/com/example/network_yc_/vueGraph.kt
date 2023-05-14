package com.example.network_yc_

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.Color
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class vueGraph : AppCompatActivity() {
    private lateinit var customView: CustomView
    private lateinit var graph: Graph
    private lateinit var drawableGraph: DrawableGraph
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var nav_view : NavigationView
    private lateinit var  toogle: ActionBarDrawerToggle



    @SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi", "ClickableViewAccessibility",
        "MissingInflatedId"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vue_graph)


        val name: String? = intent.getStringExtra("label")
        val nbpiece: String? = intent.getStringExtra("piece")

        val actionBar = supportActionBar
        actionBar?.title = "Reseau ${name}"

        graph.chargerGraph(this,"${name}")

        customView = findViewById(R.id.custom_view)

        val frameLayout = findViewById<FrameLayout>(R.id.frame_layout)

        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.addObject -> {
                    val obj = Objet("TV", RectF(50f,50f,150f,150f), Color.BLACK)
                    if (graph != null) {
                        graph.addGraphObject(obj)
                    }
                }
            }
            true
        }


    }
}
