package com.example.tp_projet_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class VoitureFragment : Fragment()
{
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

        return v
    }
}
