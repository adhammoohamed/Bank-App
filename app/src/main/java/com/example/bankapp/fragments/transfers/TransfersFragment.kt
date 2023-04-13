package com.example.bankapp.fragments.transfers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentTransfersBinding


class TransfersFragment : Fragment() {

    private lateinit var binding: FragmentTransfersBinding
    private val myAdapter = TransfersAdapter()
    private val viewModel: TransferViewModel by lazy {
        ViewModelProvider(this).get(TransferViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transfers, container, false)

        viewModel.allTransfers.observe(viewLifecycleOwner, Observer {
            myAdapter.setData(it)
        })

        binding.recyclerView.adapter = myAdapter

        return binding.root
    }


}
