package com.example.harrypottercaracters.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "sly_table")
data class SlyTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val actor: String,
    val house: String,
    val image: String,
    val name : String)