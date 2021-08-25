package com.mishrole.roomdatabase.presentation.view.fragment.update

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
import androidx.navigation.fragment.navArgs
import com.mishrole.roomdatabase.R
import com.mishrole.roomdatabase.data.entity.User
import com.mishrole.roomdatabase.databinding.FragmentListBinding
import com.mishrole.roomdatabase.databinding.FragmentUpdateBinding
import com.mishrole.roomdatabase.presentation.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val view = binding.root

        // Set current Item with Safe args to Update Edit Text
        binding.edtUpdateFirstname.setText(args.currentUser.firstname)
        binding.edtUpdateLastname.setText(args.currentUser.lastName)
        binding.edtUpdateAge.setText(args.currentUser.age.toString())

        binding.btnUpdate.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun formIsValid(firstName : String, lastName : String, age: Editable) : Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    private fun updateItem() {
        val firstName = binding.edtUpdateFirstname.text.toString()
        val lastName = binding.edtUpdateLastname.text.toString()
        val age = Integer.parseInt(binding.edtUpdateAge.text.toString())

        if(formIsValid(firstName, lastName, binding.edtUpdateAge.text)) {
            // Create User Object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            // Update Current User
            mUserViewModel.updateUser(updatedUser)
            // Show Toast Message
            Toast.makeText((requireContext()), "Successfully updated!", Toast.LENGTH_LONG).show()
            // Navigate Back to List Fragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        } else {
            Toast.makeText((requireContext()), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}