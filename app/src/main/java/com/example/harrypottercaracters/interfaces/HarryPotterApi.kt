package com.example.harrypottercaracters.interfaces

import com.example.harrypottercaracters.models.all.allCharacters
import com.example.harrypottercaracters.models.house.House
import com.example.harrypottercaracters.models.staff.Staff
import com.example.harrypottercaracters.models.students.Students
import retrofit2.Call
import retrofit2.http.GET

interface HarryPotterApi {
    @GET("api/characters")
    fun all(): Call<allCharacters>

    @GET("api/characters/house/gryffindor") //need to change the house
    fun house(): Call<House>

    @GET("api/characters/staff")
    fun staff(): Call<Staff>

    @GET("api/characters/students")
    fun students(): Call<Students>


}