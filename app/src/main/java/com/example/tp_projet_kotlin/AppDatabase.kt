package com.example.tp_projet_kotlin

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [VoitureDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun voitureDAO() : VoitureDAO

}