package com.example.harrypottercaracters

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypottercaracters.interfaces.HarryPotterApi
import com.example.harrypottercaracters.models.all.allCharacters
import com.example.harrypottercaracters.models.staff.Staff
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class HarryPotterViewModel:ViewModel() {

var test = "blah blah"
    var titleList = ArrayList<String?>()
    var dateList = ArrayList<String>()
    var urlList = ArrayList<String?>()
    var pictureList = ArrayList<String?>()
    var categoryList = ArrayList<String?>()


    override fun onCleared() {
        super.onCleared()
    }

    fun rvTester(){
        titleList.add("fsd")
        dateList.add("gsfsf")
        urlList.add("fs")
        pictureList.add("f")
        categoryList.add("add")
    }

    fun getAllCharacters(){
    val retrofit = Retrofit.Builder()
            .baseUrl("http://hp-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(HarryPotterApi::class.java)
        val call = service.all()
        test = "THIS LINE3"

        try {

            call.enqueue(object : Callback<allCharacters> {

                override fun onResponse(call: Call<allCharacters>, response: Response<allCharacters>) {

                    println("the response code is " +response.code())
                    if (response.code() == 200) {
                        val characterData = response.body()!!
                        println("response is good + The first name is " + characterData[0].name)
}
                }
                override fun onFailure(call: Call<allCharacters>, t: Throwable) {

                }
            })}catch (e: IOException) {
        }
    }

    fun getStaff(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://hp-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(HarryPotterApi::class.java)
        val call = service.staff()

        try {

            call.enqueue(object : Callback<Staff> {

                override fun onResponse(call: Call<Staff>, response: Response<Staff>) {

                    println("the response code is " +response.code())
                    if (response.code() == 200) {
                        val characterData = response.body()!!
                        test ="response is good"
                        println("response is good + The first name is " + characterData[0].name)
                    }
                }
                override fun onFailure(call: Call<Staff>, t: Throwable) {

                }
            })}catch (e: IOException) {

            e.printStackTrace()
        }

    }


}