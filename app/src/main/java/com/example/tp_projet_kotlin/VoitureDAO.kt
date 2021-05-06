package com.example.tp_projet_kotlin

import androidx.room.*

@Dao
abstract class VoitureDAO {

    @Query("SELECT * FROM voitures")
    abstract fun getListeVoitures(): List<VoitureDTO>

    @Query("SELECT COUNT(*) FROM voitures WHERE name = :name")
    abstract fun countVoituresParName(name: String): Long

    @Query("SELECT * FROM voitures WHERE name = :name")
    abstract fun getVoitureParName(name: String): Long

    @Insert
    abstract fun insert(vararg voitures: VoitureDTO)

    @Update
    abstract fun update(vararg voitures: VoitureDTO)

    @Delete
    abstract fun delete(vararg voitures: VoitureDTO)

}