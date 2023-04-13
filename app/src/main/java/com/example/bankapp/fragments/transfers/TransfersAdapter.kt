package com.example.bankapp.fragments.transfers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.databinding.TransferListItemBinding
import com.example.bankapp.model.Transfer

class TransfersAdapter : RecyclerView.Adapter<TransfersAdapter.TransfersViewHolder>(){

    private var transfersList = emptyList<Transfer>()

    class TransfersViewHolder(private val binding: TransferListItemBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(transfer: Transfer){
                binding.senderName.text = transfer.senderName
                binding.receiverTv.text = transfer.receiverName
                binding.amountNoTv.text = transfer.amount.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransfersViewHolder {
        val binding = TransferListItemBinding.inflate(LayoutInflater.from(parent.context))
        return TransfersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transfersList.size
    }

    override fun onBindViewHolder(holder: TransfersViewHolder, position: Int) {
        val currentTransfer = transfersList[position]
        holder.bind(currentTransfer)
    }

    fun setData(list: List<Transfer>){
        transfersList = list
        notifyDataSetChanged()
    }

}