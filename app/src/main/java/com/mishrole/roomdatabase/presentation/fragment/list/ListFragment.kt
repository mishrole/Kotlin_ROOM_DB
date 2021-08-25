package com.mishrole.roomdatabase.presentation.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mishrole.roomdatabase.R
import com.mishrole.roomdatabase.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}