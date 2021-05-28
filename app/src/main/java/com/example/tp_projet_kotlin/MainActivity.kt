package com.example.tp_projet_kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
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
    private var listeVoitures: MutableList<Voiture> = ArrayList()
    private val listeVoituresWGet: MutableList<Voiture> = ArrayList()
    private val voituresAdapter = VoituresAdapter(listeVoitures, this)

    private var fragment = VoitureFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //database
        loadDatabase()

        // à ajouter pour de meilleures performances :
        val liste_voitures = findViewById<RecyclerView>(R.id.liste_voitures)
        liste_voitures.setHasFixedSize(true)

        // layout manager, décrivant comment les items sont disposés :
        val layoutManager = LinearLayoutManager(this)
        liste_voitures.layoutManager = layoutManager

        getListeVoitureFromWebService(liste_voitures)

        //switch
        val sw1 = findViewById<Switch>(R.id.switch2)
        sw1?.setOnCheckedChangeListener { _, isChecked ->
            listeVoitures.clear()
            if (isChecked){
                //montre favori
                // récupérer une liste de courses :
                val listeVoitureDTO: List<VoitureDTO> = AppDatabaseHelper.getDatabase(this)
                    .voitureDAO()
                    .getListeVoitures()
                for (car in listeVoitureDTO) {
                    listeVoitures.add(
                        Voiture(car.name.toString(), car.price.toString(), car.category.toString(), car.image.toString())
                    )
                }
            }else{
                listeVoitures.addAll(listeVoituresWGet)
            }
            liste_voitures.adapter = voituresAdapter
        }
    }

    fun getListeVoitureFromWebService(liste_voitures : RecyclerView){
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
                            listeVoituresWGet.add(Voiture(c.nom, c.prixjournalierbase.toString(), c.categorieco2, url+c.image))
                        }
                        listeVoitures.addAll(listeVoituresWGet)
                        // adapter :
                        liste_voitures.adapter = voituresAdapter
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
        if(fragment.isAdded())
        {
            fragment = VoitureFragment();
        }

        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("price", price)
        bundle.putString("category", category)
        bundle.putString("image", image)
        fragment.arguments = bundle

        // transaction :
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        .add(R.id.fragment1, fragment, "exemple2")
        .addToBackStack(null)
        .commit()
    }

    fun loadDatabase(){
        Thread(Runnable {
            AppDatabaseHelper.getDatabase(this).voitureDAO().getListeVoitures()
        }).start()
    }
}
