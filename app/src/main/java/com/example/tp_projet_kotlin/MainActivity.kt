package com.example.tp_projet_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_projet_kotlin.RetrofitSingleton.courseRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        // récupération des voitures
        val listeVoitures: MutableList<Voiture> = ArrayList()
        if (ReseauHelper.estConnecte(this)) {
            courseRequest.enqueue(object : Callback<List<RetourWSGet>> {
                override fun onResponse(
                    call: Call<List<RetourWSGet>>,
                    response: Response<List<RetourWSGet>>
                ) {
                    val allCar = response.body()
                    if (allCar != null) {
                        for (c in allCar) {
                            listeVoitures.add(Voiture(c.nom, c.prixjournalierbase.toString(), c.categorieco2))

                            // adapter :
                            val voituresAdapter = VoituresAdapter(listeVoitures)
                            liste_voitures.adapter = voituresAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<RetourWSGet>>, t: Throwable) {
                    error("KO")
                    //prévoir message erreur
                }
            })
        }
    }

}
