package com.mishrole.roomdatabase.presentation.view.fragment.gps

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mishrole.roomdatabase.databinding.FragmentAddBinding
import com.mishrole.roomdatabase.databinding.FragmentGpsBinding

class GpsFragment : Fragment() {

    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    private var _binding: FragmentGpsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGpsBinding.inflate(inflater, container, false)

        binding.btnCords.setOnClickListener {

        }

        return binding.root
    }

    private fun allPermissionsGrantedGPS(): Boolean {
        TODO()
    }

    private fun getCurrentLocation() {
        TODO()
    }

}