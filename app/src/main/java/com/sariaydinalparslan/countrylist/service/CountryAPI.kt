package com.sariaydinalparslan.countrylist.service

import com.sariaydinalparslan.countrylist.model.Country
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryAPI {
//https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>>
}