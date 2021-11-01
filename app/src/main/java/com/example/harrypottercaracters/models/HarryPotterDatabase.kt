package com.example.harrypottercaracters.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harrypottercaracters.interfaces.HarryPotterDao

@Database(entities = [StaffTable::class, StudentTable::class, AllTable::class, GrifTable::class, HufTable::class,SlyTable::class,
    Ravtable::class]  , version = 2, exportSchema = false)


abstract class HarryPotterDatabase: RoomDatabase() {
    abstract  fun harryPotterDao(): HarryPotterDao

    companion object{
        @Volatile
        private var INSTANCE: HarryPotterDatabase? = null

        fun getDatabase(context: Context): HarryPotterDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HarryPotterDatabase::class.java,
                    "1"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}