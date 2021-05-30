package com.app.novia.ui.sos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.novia.databinding.FragmentSosBinding
import com.app.novia.ui.contactlist.ContactListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SosFragment : Fragment() {

    private val viewModel: SosViewModel by viewModel()
    private var _binding: FragmentSosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSosBinding.inflate(inflater, container, false)
        initializeViews()
        return binding.root
    }

    private fun initializeViews() {
        binding.sosKontakKerabat.setOnClickListener {
            val intent = Intent(context, ContactListActivity::class.java)
            startActivity(intent)
        }
        viewModel.getEmergencyContacts().observe(viewLifecycleOwner, {
            var urlString = "smsto:"

            it.forEach { contact ->
                urlString += contact.phoneNumber
                urlString += ";"
            }

            binding.sosKirimSms.setOnClickListener {
                val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(urlString))
                smsIntent.putExtra("sms_body", "Saya sedang dalam keadaan darurat")
                startActivity(smsIntent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}