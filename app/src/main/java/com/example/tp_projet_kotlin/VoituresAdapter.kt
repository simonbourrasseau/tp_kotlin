package com.example.tp_projet_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VoituresAdapter (private var listeVoitures: MutableList<Voiture>) :
    RecyclerView.Adapter<VoituresAdapter.VoitureViewHolder>()
    {
    // Crée chaque vue item à afficher :
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoitureViewHolder
    {
        val viewVoiture = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_voiture, parent, false)
        return VoitureViewHolder(viewVoiture)
    }

    // Renseigne le contenu de chaque vue item :
    override fun onBindViewHolder(holder: VoitureViewHolder, position: Int)
    {
        holder.textViewLibelleVoiture.text = listeVoitures[position].name
        holder.textViewPriceVoiture.text = listeVoitures[position].price
        holder.textViewCategoryVoiture.text = listeVoitures[position].category
    }

    override fun getItemCount(): Int = listeVoitures.size

    // ViewHolder :
    inner class VoitureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewLibelleVoiture: TextView = itemView.findViewById(R.id.libelle_voiture)
        val textViewPriceVoiture: TextView = itemView.findViewById(R.id.price_voiture)
        val textViewCategoryVoiture: TextView = itemView.findViewById(R.id.category_voiture)
    }

}