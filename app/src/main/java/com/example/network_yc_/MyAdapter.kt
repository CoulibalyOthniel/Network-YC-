package com.example.network_yc_

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter(private val context:Activity, private val arrayList: ArrayList<Graph>) : ArrayAdapter<Graph>(context, R.layout.list_network,arrayList) {

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_network, null)

        val networkName : TextView = view.findViewById(R.id.nameNetwork)
        val numberPiece : TextView = view.findViewById(R.id.PlanAppart)


        networkName.text = arrayList[position].label
        numberPiece.text = arrayList[position].nbpiece



        return view
    }
}