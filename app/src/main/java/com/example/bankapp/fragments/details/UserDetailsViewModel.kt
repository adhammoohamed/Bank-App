package com.example.bankapp.fragments.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bankapp.data.CustomersDatabase
import com.example.bankapp.model.Customer
import com.example.bankapp.model.Transfer
import com.example.bankapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val database = CustomersDatabase.getDatabase(application.baseContext)
    private val repository = Repository(database)


    private var _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>> get() = _customers

    private var _receiversName = MutableLiveData<List<String>>()
    val receiversName: LiveData<List<String>> get() = _receiversName

    fun getReceivers(id: Int) {
        viewModelScope.launch {
            _customers.value = repository.getAllCustomersPreventOne(id)
            val customerList = _customers.value
            _receiversName.value = customerList!!.map { it.name }
        }
    }



    fun addTransfer(transfer: Transfer){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTransaction(transfer)
        }
    }

    fun addCustomer(customer: Customer){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(customer)
        }
    }
}