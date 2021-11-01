package com.example.harrypottercaracters.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harrypottercaracters.models.*

@Dao
interface HarryPotterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStaff(staff: StaffTable)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllStaffData(): LiveData< List<StaffTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(all: AllTable)
    @Query("SELECT * FROM all_table ORDER BY id ASC")
    fun readAllData(): LiveData< List<AllTable>>

    //student
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(student: StudentTable)
    @Query("SELECT * FROM student_table ORDER BY id ASC")
    fun readStudentData(): LiveData< List<StudentTable>>


    //Grif
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGrif(grif: GrifTable)
    @Query("SELECT * FROM grif_table ORDER BY id ASC")
    fun readGrifData(): LiveData< List<GrifTable>>

//Huf
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHuf(huf: HufTable)
    @Query("SELECT * FROM huf_table ORDER BY id ASC")
    fun readHufData(): LiveData< List<HufTable>>

//Sly
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSly(sly: SlyTable)
    @Query("SELECT * FROM sly_table ORDER BY id ASC")
    fun readSlyData(): LiveData< List<SlyTable>>

    //Rav
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRav(rav: Ravtable)
    @Query("SELECT * FROM rav_table ORDER BY id ASC")
    fun readRavData(): LiveData< List<Ravtable>>


}