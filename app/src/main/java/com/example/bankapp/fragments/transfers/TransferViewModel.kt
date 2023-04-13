package com.example.bankapp.fragments.transfers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.bankapp.data.CustomersDatabase
import com.example.bankapp.model.Transfer
import com.example.bankapp.repository.Repository

class TransferViewModel(application: Application) : AndroidViewModel(application) {
    private val database = CustomersDatabase.getDatabase(application.baseContext)
    private val repository = Repository(database)

//    private var _allTransfers = MediatorLiveData<List<Transfer>>()
    val allTransfers: LiveData<List<Transfer>>

    /*get() = _allTransfers*/
    init {
        allTransfers = repository.getAllTransactions()
    }
}