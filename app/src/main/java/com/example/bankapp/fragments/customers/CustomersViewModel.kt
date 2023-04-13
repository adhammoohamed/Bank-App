package com.example.bankapp.fragments.customers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bankapp.data.CustomersDatabase
import com.example.bankapp.model.Customer
import com.example.bankapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomersViewModel(application: Application) : AndroidViewModel(application) {

    private val database = CustomersDatabase.getDatabase(application.baseContext)
    private val repository: Repository = Repository(database)
    val readAll: LiveData<List<Customer>>


    init {
        readAll = repository.getAllCustomers()
    }

    fun searchQuery(query: String): LiveData<List<Customer>>{
        return repository.searchQuery(query)
    }


}