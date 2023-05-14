package com.example.network_yc_

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class vueGraph : AppCompatActivity() {

    private lateinit var graphs: ArrayList<Graph>
    private lateinit var drawableGraph: DrawableGraph

    @SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vue_graph)

        // Récupérer les données transmises via l'intent
        val name: String? = intent.getStringExtra("label")
        val nbpiece: String? = intent.getStringExtra("piece")

        val actionBar = supportActionBar
        actionBar?.title = "Reseau ${name}"

        // Récupérer les données depuis le fichier JSON
        val jsonString = File(applicationContext.filesDir, "graphs.json").readText()
        graphs = Gson().fromJson(jsonString, object : TypeToken<ArrayList<Graph>>() {}.type)

        // Récupérer le Graph correspondant au nom transmis via l'intent
        var graphLoad: Graph? = graphs.find { it.label == "${name}" }

        // Afficher l'image correspondant à la pièce sélectionnée
        val imageView = findViewById<ImageView>(R.id.imageReseau)
        val resourceId = resources.getIdentifier(nbpiece, "drawable", packageName)
        val myDrawable = resources.getDrawable(resourceId, null)
        imageView.setImageDrawable(myDrawable)

        val frameLayout = findViewById<FrameLayout>(R.id.frame_layout)

        // Afficher le DrawableGraph
        drawableGraph = DrawableGraph(this, graphLoad)
        frameLayout.background = drawableGraph

        // Ajouter un objet au Graph lorsque l'utilisateur appuie sur l'écran
        frameLayout.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val obj = Objet("", motionEvent.x, motionEvent.y)
                    graphLoad?.addGraphObject(obj)

                    // Vérifier si l'objet a été ajouté avec succès avant de dessiner le DrawableGraph
                    if (graphLoad?.objets?.contains(obj) == true) {
                        drawableGraph.graph = graphLoad
                        frameLayout.background = drawableGraph
                    }
                }

                MotionEvent.ACTION_MOVE -> {
                    // Déplacer l'objet lorsque l'utilisateur fait glisser son doigt
                    val obj = graphLoad?.objets?.firstOrNull()

                    if (obj != null) {
                        obj.x = motionEvent.x
                    }
                    if (obj != null) {
                        obj.y = motionEvent.y
                    }

                    drawableGraph.graph = graphLoad
                    frameLayout.background = drawableGraph
                }
            }
            true

        }


    }
}
