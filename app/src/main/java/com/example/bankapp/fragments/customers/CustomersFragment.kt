package com.example.bankapp.fragments.customers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentCustomersBinding

class CustomersFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: CustomersViewModel by lazy {
        ViewModelProvider(this)[CustomersViewModel::class.java]
    }
    private lateinit var binding: FragmentCustomersBinding
    private val myAdapter = CustomersAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customers, container, false)

        binding.recyclerView.adapter = myAdapter

        viewModel.readAll.observe(viewLifecycleOwner) {
            myAdapter.setData(it)
        }
        val toolbar = binding.toolbar
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        val search = menu.findItem(R.id.search_ic)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.transfersList) {
            findNavController().navigate(R.id.action_customersFragment_to_transfersFragment)
        }

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchDatabase(query)
        }

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchDatabase(query)
        }

        return true
    }


    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchQuery(searchQuery).observe(viewLifecycleOwner, Observer {
            myAdapter.setData(it)
        })
    }
}