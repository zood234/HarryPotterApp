package com.example.harrypottercaracters

import android.app.Application
import androidx.lifecycle.*
import com.example.harrypottercaracters.interfaces.HarryPotterApi
import com.example.harrypottercaracters.models.*
import com.example.harrypottercaracters.models.all.All
import com.example.harrypottercaracters.models.house.House
import com.example.harrypottercaracters.models.staff.Staffs
import com.example.harrypottercaracters.models.staff.StaffsItem
import com.example.harrypottercaracters.models.students.Students
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class HarryPotterViewModel(application: Application): AndroidViewModel(application){

    var allLiveData : MutableLiveData<All>  = MutableLiveData()
    var houseLiveData : MutableLiveData<House>  = MutableLiveData()
    var studentLiveData : MutableLiveData<Students>  = MutableLiveData()
    var staffLiveData : MutableLiveData<Staffs> = MutableLiveData()


    val readAllStaffData: LiveData<List<StaffTable>>
    val readAllData: LiveData<List<AllTable>>
//
   val readStudentData: LiveData<List<StudentTable>>
    val readGrifData: LiveData<List<GrifTable>>
    val readHufData: LiveData<List<HufTable>>
    val readSlyData: LiveData<List<SlyTable>>
    val readRavData: LiveData<List<Ravtable>>
     var internetOff: Boolean = false
    lateinit var internetStatus: LiveData< Boolean>

    private  val repository: HarryPotterRepository

    init {
        val userDao = HarryPotterDatabase.getDatabase(application).harryPotterDao()
        repository = HarryPotterRepository(userDao)
        readAllStaffData =repository.readAllStaffData
        readAllData =repository.readAllData

       readGrifData =repository.readGrifData
       readStudentData =repository.readStudentData
        readSlyData =repository.readSlyData
        readHufData =repository.readHufData
        readRavData =repository.readRavData
    }

    var staffsItemLiveData : MutableLiveData<StaffsItem> = MutableLiveData()


fun getAllUsers(){

}
    override fun onCleared() {
        super.onCleared()
    }




    fun getAllLiveObserver(): MutableLiveData<All>{
    return allLiveData
}

    fun getHouseLiveObserver(): MutableLiveData<House>{
        return houseLiveData
    }
    fun getStudentLiveObserver(): MutableLiveData<Students>{
        return studentLiveData
    }
    fun getStaffLiveObserver(): MutableLiveData<Staffs>{
        return staffLiveData
    }


    fun makeAPiAllCall(){
        viewModelScope.launch(Dispatchers.IO) {
            try {


            val retroInstance = RetroInstance.getInstance().create(HarryPotterApi::class.java)
            val response = retroInstance.getAll()

            allLiveData.postValue(response)

                for (i in 0 until response.size) {
                    val allTable = AllTable(i,response[i].actor, response[i].actor, response[i].image, response[i].name)
                    addAllUser(allTable)
                }
            }
            catch (e: IOException) {
            e.printStackTrace()
                println("No INTERNET")
                internetOff = true
            }
        }
    }

    fun makeAPiHouseCall(houseType : String){

        try{
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getInstance().create(HarryPotterApi::class.java)
            println("THE HOUSE IS : +$houseType")
            val response = retroInstance.getAllHouse(houseType)

            if(houseType == "gryffindor"){

            for (i in 0 until response.size) {
            val grifTable = GrifTable(i,response[i].actor, response[i].actor, response[i].image, response[i].name)
            addGrif(grifTable)
          }
            }

            if (houseType == "Slytherin") {
            for (i in 0 until response.size) {
            val slyTable = SlyTable(i,response[i].actor, response[i].actor, response[i].image, response[i].name)
            addSly(slyTable)
           }
            }

        if(houseType == "Hufflepuff"){

            for (i in 0 until response.size) {
            val hufTable = HufTable(i,response[i].actor, response[i].actor, response[i].image, response[i].name)
            addHuff(hufTable)
           }


        }
        if(houseType == "Ravenclaw"){

            for (i in 0 until response.size) {
                val ravtable = Ravtable(i,response[i].actor, response[i].actor, response[i].image, response[i].name)
                addRav(ravtable)}
        }

            houseLiveData.postValue(response)
        }}
        catch (e: IOException) {
            e.printStackTrace()
            println("No INTERNET")
            internetOff = true
        }
    }

    fun makeAPiStudentCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getInstance().create(HarryPotterApi::class.java)
            val response = retroInstance.getAllStudents()

            studentLiveData.postValue(response)

try {


    for (i in 0 until response.size) {
        val studentTable = StudentTable(
            i,
            response[i].actor,
            response[i].actor,
            response[i].image,
            response[i].name
        )
        addStudentUser(studentTable)
    }
}           catch (e: IOException) {
    e.printStackTrace()
    println("No INTERNET")
    internetOff = true
}

        }


    }


    fun makeAPiStaffCall(){
        try {


        viewModelScope.launch(Dispatchers.IO) {
           val retroInstance = RetroInstance.getInstance().create(HarryPotterApi::class.java)
            val response = retroInstance.getAllStaff()
            staffLiveData.postValue(response)

            for (i in 0 until response.size) {
            val staffTable = StaffTable(i,response[i].actor, response[i].actor, response[i].image, response[i].name)
            addStaffUser(staffTable)
           }
        }}
        catch (e: IOException) {
            e.printStackTrace()
            println("No INTERNET")
            internetOff = true
        }
    }



    fun addStaffUser(staffTable: StaffTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(staffTable)

        }
    }
    fun addAllUser(allTable: AllTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAll(allTable)

        }
    }
    fun addStudentUser(studentTable: StudentTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(studentTable)

        }
    }



    fun addGrif(grifTable: GrifTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGrif(grifTable)

        }
    }

    fun addSly(slyTable: SlyTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSly(slyTable)

        }
    }

    fun addHuff(hufTable: HufTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHuf(hufTable)

        }
    }

    fun addRav(ravtable: Ravtable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRav(ravtable)

        }
    }

    fun insertIntoUserDatabase(){

        println("THE DATABASE ADDED PROPERLY")

    }

    fun readUserData(){
        readAllData

    }




}

