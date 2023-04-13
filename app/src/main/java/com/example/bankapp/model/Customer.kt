package com.example.bankapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "customers_table")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ,
    val accNo: Long,
    var name: String,
    var balance: Int
) : Parcelable
