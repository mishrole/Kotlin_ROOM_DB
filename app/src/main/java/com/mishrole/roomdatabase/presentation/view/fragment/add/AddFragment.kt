package com.mishrole.roomdatabase.presentation.view.fragment.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mishrole.roomdatabase.R
import com.mishrole.roomdatabase.data.entity.User
import com.mishrole.roomdatabase.databinding.FragmentAddBinding
import com.mishrole.roomdatabase.presentation.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        val view = binding.root

        // Pass the Context and UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = binding.edtAddFirstname.text.toString()
        val lastName = binding.edtAddLastname.text.toString()
        val age = binding.edtAddAge.text

        if(formIsValid(firstName, lastName, age)) {
            // Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            // Add Data to Database
            mUserViewModel.addUser(user)
            // Show Toast Message
            Toast.makeText((requireContext()), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText((requireContext()), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun formIsValid(firstName : String, lastName : String, age: Editable) : Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}