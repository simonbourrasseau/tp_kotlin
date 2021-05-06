package com.example.tp_projet_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "voitures")
class VoitureDTO(
        @PrimaryKey(autoGenerate = true)
        val voitureId: Long = 0,

        @ColumnInfo(name = "name")
        var name: String? = null,

        @ColumnInfo(name = "price")
        var price: Int? = null,

        @ColumnInfo(name = "category")
        var category: String? = null,

        @ColumnInfo(name = "image")
        var image: String? = null)

