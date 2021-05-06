package com.example.tp_projet_kotlin

import android.R.attr.button
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


class VoitureFragment : Fragment()
{
    private var isAlreadyInFavorite : Int = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val v = inflater.inflate(R.layout.fragment_voiture, container, false)

        val context = activity as AppCompatActivity
        val textViewLibelleVoiture: TextView = v.findViewById(R.id.libelle_voiture_fragment)
        val textViewPriceVoiture: TextView = v.findViewById(R.id.price_voiture_fragment)
        val textViewCategoryVoiture: TextView = v.findViewById(R.id.category_voiture_fragment)
        val imageViewUrlVoiture : ImageView = v.findViewById(R.id.image_voiture_fragment)
        val buttonView : Button = v.findViewById(R.id.addFavori)

        val name = arguments?.getString("name")
        val price = arguments?.getString("price")
        val category = arguments?.getString("category")
        val image = arguments?.getString("image")


        name?.let {
            textViewLibelleVoiture.text = name
        }

        price?.let {
            textViewPriceVoiture.text = price
        }

        category?.let {
            textViewCategoryVoiture.text = category
        }

        image?.let {
            Picasso.get().load(image).into(imageViewUrlVoiture);
        }

        //verifie si il n'est pas déjà dans des favori
        verifIfAlreadyInFavorite(name.toString(), buttonView, context)

        buttonView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?)
            {
                var voiture = VoitureDTO()

                if(isAlreadyInFavorite == 0) {
                    voiture.name = name
                    voiture.price = price?.toInt()
                    voiture.category = category
                    voiture.image = image

                    AppDatabaseHelper.getDatabase(context).voitureDAO().insert(voiture)
                }else{
                    Log.d("hoy", "kio")
                    voiture = AppDatabaseHelper.getDatabase(context).voitureDAO().getVoitureParName(name.toString())
                    AppDatabaseHelper.getDatabase(context).voitureDAO().delete(voiture)
                }

                verifIfAlreadyInFavorite(name.toString(), buttonView, context)
            }
        })

        return v
    }

    fun verifIfAlreadyInFavorite(name : String, buttonView : Button, context : AppCompatActivity){
        val voitureAlreadyInFavorite = AppDatabaseHelper.getDatabase(context).voitureDAO().countVoituresParName(name).toString()
        if(voitureAlreadyInFavorite == "0"){
            buttonView.text = "Ajouter aux favoris"
            isAlreadyInFavorite = 0
        }else{
            buttonView.text = "Retirer des favoris"
            isAlreadyInFavorite = 1
        }
    }
}
