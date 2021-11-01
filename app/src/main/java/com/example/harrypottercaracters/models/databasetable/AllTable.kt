package com.example.harrypottercaracters.models.databasetable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_table")
data class AllTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val actor: String,
    val house: String,
    val image: String,
    val name : String)