package com.example.bankapp.fragments.details

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentUserDetialsBinding
import com.example.bankapp.model.Customer
import com.example.bankapp.model.Transfer
import java.util.*


@Suppress("DEPRECATION")
class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetialsBinding
    var currentUser: Customer? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(UserDetailsViewModel::class.java)
    }
    private lateinit var receiver: Customer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_detials, container, false)


        currentUser = arguments?.getParcelable("customer")

        binding.enterName.text = currentUser!!.name
        binding.accNoEnter.text = currentUser!!.accNo.toString()
        binding.balanceEnter.text = currentUser!!.balance.toString()

        viewModel.getReceivers(currentUser!!.id)

        viewModel.receiversName.observe(viewLifecycleOwner) {
            val receivers = it
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                receivers
            )

            binding.receiver.setAdapter(adapter)

            binding.receiver.setOnItemClickListener { _, _, position, _ ->
                val customersList = viewModel.customers.value
                receiver = customersList!![position]
            }
        }


        binding.transferBtn.setOnClickListener {
            fader()
            translater()
            showItems()
        }

        binding.doneBtn.setOnClickListener {
            if (binding.receiver.text.isNullOrBlank() || binding.amount.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "You did not transfer it", Toast.LENGTH_SHORT)
                    .show()
            } else {
                checkTheAmount()
            }
        }

        return binding.root
    }

    private fun showItems() {
        binding.receiverTi.visibility = View.VISIBLE
        binding.amountTi.visibility = View.VISIBLE
        binding.amount.visibility = View.VISIBLE
        binding.doneBtn.visibility = View.VISIBLE
    }

    private fun fader() {
        val animator = ObjectAnimator.ofFloat(binding.transferBtn, View.ALPHA, 0f)
        animator.duration = 100
        animator.start()
    }

    private fun translater() {
        val receiverAnimator = ObjectAnimator.ofFloat(binding.receiverTi, View.TRANSLATION_Y, -300f)
        receiverAnimator.duration = 600
        receiverAnimator.start()

        val amountAnimator = ObjectAnimator.ofFloat(binding.amountTi, View.TRANSLATION_Y, -300f)
        amountAnimator.duration = 600
        amountAnimator.start()

        val doneAnimator = ObjectAnimator.ofFloat(binding.doneBtn, View.TRANSLATION_Y, -300f)
        doneAnimator.duration = 600
        doneAnimator.start()
    }


    private fun checkTheAmount() {
        val amount = binding.amount.text.toString().toInt()
        if (currentUser!!.balance < amount) {
            Toast.makeText(requireContext(), "Your balance is not enough", Toast.LENGTH_SHORT)
                .show()
        } else {
            manageTransfer()
            makeTransfer()
            findNavController().navigate(R.id.action_userDetailsFragment_to_customersFragment)
            Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
        }
    }


    private fun makeTransfer() {
        val amount = binding.amount.text.toString().toInt()
        val transaction = Transfer(0, currentUser!!.name, receiver.name, amount)
        viewModel.addTransfer(transaction)
    }


    private fun manageTransfer(){
        val amount = binding.amount.text.toString().toInt()
        currentUser!!.balance = (currentUser!!.balance - amount)
        viewModel.addCustomer(currentUser!!)
        receiver.balance = (receiver.balance + amount)
        viewModel.addCustomer(receiver)
    }

}
