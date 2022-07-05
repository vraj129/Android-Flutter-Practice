package com.example.kotlinpractice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryData(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val id:Int = 0,
    @ColumnInfo(name = "name") val country:String?,
    @ColumnInfo(name = "countryInfo")
    val countryInfo:CountryInfo?)
