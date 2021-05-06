package com.example.tp_projet_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_projet_kotlin.RetrofitSingleton.courseRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), VoituresAdapter.OnItemClickListener {

    private val url = "http://s519716619.onlinehome.fr/exchange/madrental/images/"
    private val listeVoitures: MutableList<Voiture> = ArrayList()
    private val voituresAdapter = VoituresAdapter(listeVoitures, this)

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
        if (ReseauHelper.estConnecte(this)) {
            courseRequest.enqueue(object : Callback<List<RetourWSGet>> {
                override fun onResponse(
                    call: Call<List<RetourWSGet>>,
                    response: Response<List<RetourWSGet>>
                ) {
                    val allCar = response.body()
                    if (allCar != null) {
                        for (c in allCar) {
                            listeVoitures.add(Voiture(c.nom, c.prixjournalierbase.toString(), c.categorieco2, url+c.image))
                            Log.d("tag", "${c.image}")
                            // adapter :
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

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item ${listeVoitures[position].name} clicked", Toast.LENGTH_SHORT).show()
        changefragment(listeVoitures[position].name, listeVoitures[position].price, listeVoitures[position].category, listeVoitures[position].image)
    }

    fun changefragment(name : String, price : String, category : String, image : String){
        // fragment :
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("price", price)
        bundle.putString("category", category)
        bundle.putString("image", image)
        val fragment = VoitureFragment()
        fragment.arguments = bundle

        // transaction :
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment1, fragment, "exemple2")
        transaction.commit()
    }
}
