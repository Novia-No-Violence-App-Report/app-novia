package com.app.novia.ui.sos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.novia.databinding.FragmentSosBinding
import com.app.novia.ui.contactlist.ContactListActivity

class SosFragment : Fragment() {
    private lateinit var homeViewModel: SosViewModel
    private var _binding: FragmentSosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(SosViewModel::class.java)

        _binding = FragmentSosBinding.inflate(inflater, container, false)
        initializeViews()
        return binding.root
    }

    private fun initializeViews() {
        binding.sosKontakKerabat.setOnClickListener {
            val intent = Intent(context, ContactListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}