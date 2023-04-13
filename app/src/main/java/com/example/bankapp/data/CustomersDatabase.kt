package com.example.bankapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bankapp.model.Customer
import com.example.bankapp.model.Transfer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Customer::class,Transfer::class], version = 1, exportSchema = false)
abstract class CustomersDatabase : RoomDatabase() {

    abstract val customerDao: CustomersDao
    abstract val transferDao: TransferDao

    companion object {

        @Volatile
        lateinit var INSTANCE: CustomersDatabase

        fun getDatabase(context: Context): CustomersDatabase {

            if (!::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CustomersDatabase::class.java,
                        "customer_database"
                    ).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            INSTANCE.let { it ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    it.customerDao.insertCustomers(customers)
                                }
                            }
                        }
                    }).build()
                }
            }
            return INSTANCE
        }
    }
}

val customers: ArrayList<Customer> = arrayListOf(
    Customer(accNo = 1532546564, name = "Adham Mohamed", balance = 15000),
    Customer(accNo = 4684654654, name = "Ali Amr", balance = 3000),
    Customer(accNo = 2552154645, name = "Youssef Mostafa", balance = 2000),
    Customer(accNo = 4256546562, name = "Omar Mostafa", balance = 3000),
    Customer(accNo = 9815460152, name = "Khaled Ali", balance = 8000),
    Customer(accNo = 5349698653, name = "Abdo Mohamed", balance = 9000),
    Customer(accNo = 2698354345, name = "Roaa Mostafa", balance = 56000),
    Customer(accNo = 5553245364, name = "Ahmed Amr", balance = 23000),
    Customer(accNo = 5756189879, name = "Ibrahim Ali", balance = 77000),
    Customer(accNo = 3468486879, name = "Kareem Ahmed", balance = 65000),

    )

