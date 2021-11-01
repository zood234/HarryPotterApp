package com.example.harrypottercaracters.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "rav_table")
data class Ravtable(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val actor: String,
    val house: String,
    val image: String,
    val name : String)