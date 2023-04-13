package com.example.bankapp.repository

import androidx.lifecycle.LiveData
import com.example.bankapp.data.CustomersDatabase
import com.example.bankapp.model.Customer
import com.example.bankapp.model.Transfer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(database: CustomersDatabase) {

    private val customersDao = database.customerDao
    private val transferDao = database.transferDao


    fun searchQuery(query: String): LiveData<List<Customer>>{
        return customersDao.searchQuery(query)
    }

    suspend fun getAllCustomersPreventOne(id: Int): List<Customer> {
        var receivers: List<Customer>
        withContext(Dispatchers.IO) {
            receivers = customersDao.getAllCustomerPreventOne(id)
        }
        return receivers
    }

    fun getAllCustomers(): LiveData<List<Customer>> {
        return customersDao.gatAllCustomers()
    }

    fun getAllTransactions(): LiveData<List<Transfer>> {
        return transferDao.readAllTransfers()
    }

    suspend fun insertTransaction(transfer: Transfer) {
        transferDao.insertTransfer(transfer)
    }

    suspend fun addCustomer(customer: Customer){
        customersDao.addCustomer(customer)
    }
}