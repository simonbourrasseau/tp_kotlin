package com.example.tp_projet_kotlin

object Model {
    data class Car(
        val id: String,
        val nom: String,
        val image: String,
        val disponible: String,
        val prixjournalierbase: String,
        val promotion: String,
        val agemin: String,
        val categorieco2: String,
        val equipements: Array<Any>,
        val options: Array<Any>
    )
}