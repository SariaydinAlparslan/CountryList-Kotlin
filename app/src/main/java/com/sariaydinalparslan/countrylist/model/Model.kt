package com.sariaydinalparslan.countrylist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName : String?,
    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val capitalName : String?,
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val currencyName : String?,
    @ColumnInfo(name = "region")
    @SerializedName("region")
    val regionName : String?,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    val languageName : String?,
    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl : String?){

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}

//Primary key roomdayken constractor a almak yerine body de kullanacaz