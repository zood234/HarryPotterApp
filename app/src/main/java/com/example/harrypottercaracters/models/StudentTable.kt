package com.example.harrypottercaracters.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val actor: String,
    val house: String,
    val image: String,
    val name : String)