package com.sariaydinalparslan.countrylist.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sariaydinalparslan.countrylist.model.Country


@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase :RoomDatabase() {

    abstract fun countryDao(): CountryDao

    //compainon object olması nedeni farklı threadlerde çağırabilimek
    companion object{
        //Volatile farklı threadlere görünür kılmasını sağlar
       @Volatile private var instance : CountryDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context)= instance ?: synchronized(lock){
        instance ?: makeDataBase(context).also {
            instance = it
            }
        }
        private fun makeDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()
    }
}