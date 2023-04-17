package com.example.network_yc_

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val context :Activity, private val arrayList: ArrayList<Network>) : ArrayAdapter<Network>(context, R.layout.list_network,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_network, null)


        val imageView : ImageView = view.findViewById(R.id.imageNetwork)
        val network_name : TextView = view.findViewById(R.id.nameNetwork)
        val number_piece : TextView = view.findViewById(R.id.PlanAppart)

        imageView.setImageResource(arrayList[position].networkImage)
        network_name.text = arrayList[position].nom_reseau
        number_piece.text = arrayList[position].number_piece



        return view
    }
}