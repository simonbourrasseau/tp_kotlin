package com.example.tp_projet_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // à ajouter pour de meilleures performances :
        val liste_voitures = findViewById<RecyclerView>(R.id.liste_voitures)
        liste_voitures.setHasFixedSize(true)

        // layout manager, décrivant comment les items sont disposés :
        val layoutManager = LinearLayoutManager(this)
        liste_voitures.layoutManager = layoutManager

        // contenu d'exemple :
        val listeVoitures: MutableList<Voiture> = ArrayList()
        listeVoitures.add(Voiture("Buggy", "19", "G"))
        listeVoitures.add(Voiture("Damn Car", "12", "E"))

        // adapter :
        val voituresAdapter = VoituresAdapter(listeVoitures)
        liste_voitures.adapter = voituresAdapter
    }
}