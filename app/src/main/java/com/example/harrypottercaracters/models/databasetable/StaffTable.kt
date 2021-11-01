package com.example.harrypottercaracters.models.databasetable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")

data class StaffTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val actor: String,
    val house: String,
    val image: String,
    val name : String)