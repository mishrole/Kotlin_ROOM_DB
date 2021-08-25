package com.mishrole.roomdatabase.presentation.view.fragment.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mishrole.roomdatabase.R
import com.mishrole.roomdatabase.databinding.FragmentListBinding
import com.mishrole.roomdatabase.presentation.view.adapter.ListAdapter
import com.mishrole.roomdatabase.presentation.viewmodel.UserViewModel

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val view = binding.root

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // Notify change
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Error: Menu propagated on every fragment
        // Add Menu (Delete All Users)
        //setHasOptionsMenu(true)

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_icon) {
            deleteAllUsers()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        // If delete icon is pressed, ask for confirmation
        val builder = AlertDialog.Builder(requireContext())

        // Confirm
        builder.setPositiveButton("Yes") { _, _ ->
            // Delete Current User
            mUserViewModel.deleteAllUsers()
            // Show Toast Message
            Toast.makeText(requireContext(), "Successfully removed all Users", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") { _, _ -> }

        builder.setTitle("Delete All Users")
        builder.setMessage("Are you sure you want to delete all Users?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}