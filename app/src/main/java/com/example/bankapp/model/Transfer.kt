package com.example.bankapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("transfers_table")
data class Transfer(
    @PrimaryKey(true)
    val id: Int,
    val senderName: String,
    val receiverName: String,
    val amount: Int
)
