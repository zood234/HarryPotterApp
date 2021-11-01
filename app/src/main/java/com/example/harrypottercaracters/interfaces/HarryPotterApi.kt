package com.example.harrypottercaracters.interfaces

import com.example.harrypottercaracters.models.all.All
import com.example.harrypottercaracters.models.staff.Staffs
import com.example.harrypottercaracters.models.house.House
import com.example.harrypottercaracters.models.students.Students
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HarryPotterApi {

    @GET("api/characters")
    suspend fun getAll(): All

    @GET("api/characters/house/{house}")
    suspend fun getAllHouse(@Path("house") house: String ): House

    @GET("api/characters/students")
    suspend fun getAllStudents(): Students

    @GET("api/characters/staff")
    suspend fun getAllStaff(): Staffs

}