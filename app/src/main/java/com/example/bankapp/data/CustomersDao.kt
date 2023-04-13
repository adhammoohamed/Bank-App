package com.example.bankapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bankapp.model.Customer
import kotlinx.coroutines.Deferred

@Dao
interface CustomersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomers(customers: ArrayList<Customer>)

    @Query("SELECT * FROM customers_table WHERE id = :customerId")
    suspend fun getCustomerById(customerId: Int): Customer

    @Query("SELECT * FROM customers_table WHERE id <> :customerId")
    fun getAllCustomerPreventOne(customerId: Int): List<Customer>

    @Query("SELECT * FROM customers_table ORDER BY id ASC")
    fun gatAllCustomers(): LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCustomer(customer: Customer)

    @Query("SELECT * from customers_table WHERE name LIKE :query")
    fun searchQuery(query: String) : LiveData<List<Customer>>
}