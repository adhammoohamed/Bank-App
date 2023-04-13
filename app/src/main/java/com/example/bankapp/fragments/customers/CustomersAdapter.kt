package com.example.bankapp.fragments.customers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R
import com.example.bankapp.databinding.CustomerItemBinding
import com.example.bankapp.model.Customer
import java.text.NumberFormat
import java.util.*

class CustomersAdapter : RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder>() {

    private var customersList = emptyList<Customer>()

    class CustomerViewHolder(val binding: CustomerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(customer: Customer) {
            val numberFormat = NumberFormat.getCurrencyInstance()
            numberFormat.currency = Currency.getInstance("EGP")
            binding.name.text = customer.name
            binding.accNo.text = customer.accNo.toString()
            binding.balance.text = numberFormat.format(customer.balance)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomerItemBinding.inflate(layoutInflater)
        return CustomerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customersList.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentCustomer = customersList[position]
        holder.bind(currentCustomer)
        holder.itemView.setOnClickListener {
            val action = CustomersFragmentDirections.actionCustomersFragmentToUserDetailsFragment(
                currentCustomer
            )
            it.findNavController().navigate(action)
        }
    }

    fun setData(customer: List<Customer>) {
        customersList = customer
        notifyDataSetChanged()
    }
}