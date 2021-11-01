package com.example.harrypottercaracters.models

import androidx.lifecycle.LiveData
import com.example.harrypottercaracters.interfaces.HarryPotterDao

class HarryPotterRepository(private  val harryPotterDao : HarryPotterDao) {

    val readAllStaffData: LiveData< List<StaffTable>> = harryPotterDao.readAllStaffData()
    suspend fun addUser(staff:StaffTable ){
        harryPotterDao.addStaff(staff)
    }

    val readAllData: LiveData<List<AllTable>> = harryPotterDao.readAllData()
    suspend fun addAll(all:AllTable ){
        harryPotterDao.addAll(all)
    }

    //Student
    val readStudentData: LiveData<List<StudentTable>> = harryPotterDao.readStudentData()
    suspend fun addStudent(student:StudentTable ){
        harryPotterDao.addStudent(student)
    }
//Grif
    val readGrifData: LiveData<List<GrifTable>> = harryPotterDao.readGrifData()
    suspend fun addGrif(grif:GrifTable ){
        harryPotterDao.addGrif(grif)
    }

    //Sly
    val readSlyData: LiveData<List<SlyTable>> = harryPotterDao.readSlyData()
    suspend fun addSly(sly:SlyTable ){
        harryPotterDao.addSly(sly)
    }

    //Huf
    val readHufData: LiveData<List<HufTable>> = harryPotterDao.readHufData()
    suspend fun addHuf(huf:HufTable ){
        harryPotterDao.addHuf(huf)
    }

//Rav
    val readRavData: LiveData<List<Ravtable>> = harryPotterDao.readRavData()
    suspend fun addRav(rav:Ravtable ){
        harryPotterDao.addRav(rav)
    }





}