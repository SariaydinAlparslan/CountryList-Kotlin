package com.sariaydinalparslan.countrylist.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sariaydinalparslan.countrylist.model.Country
import io.reactivex.Flowable

//vararg -> multiple country object
//List<Long> -> primary key

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll (vararg countries: Country) : List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

}