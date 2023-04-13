package com.example.bankapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bankapp.model.Transfer

@Dao
interface TransferDao {

    @Query("SELECT * FROM transfers_table ORDER BY id ASC")
    fun readAllTransfers(): LiveData<List<Transfer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransfer(transaction: Transfer)
}