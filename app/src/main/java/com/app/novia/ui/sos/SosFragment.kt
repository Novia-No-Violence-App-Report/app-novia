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
        val callIntent = Intent(Intent.ACTION_DIAL)
        val contactIntent = Intent(context, ContactListActivity::class.java)
        binding.sosKontakKerabat.setOnClickListener {
            startActivity(contactIntent)
        }
        binding.sosKontakKerabatMessage.setOnClickListener {
            startActivity(contactIntent)
        }
        binding.cardSapa.setOnClickListener {
            callIntent.data = Uri.parse("tel:129")
            startActivity(callIntent)
        }
        binding.cardEmergency.setOnClickListener {
            callIntent.data = Uri.parse("tel:112")
            startActivity(callIntent)
        }
        binding.cardPolisi.setOnClickListener {
            callIntent.data = Uri.parse("tel:110")
            startActivity(callIntent)
        }
        binding.cardAmbulans.setOnClickListener {
            callIntent.data = Uri.parse("tel:119")
            startActivity(callIntent)
        }
        viewModel.getEmergencyContacts().observe(viewLifecycleOwner, {
            var urlString = "smsto:"

            it.forEach { contact ->
                urlString += contact.phoneNumber
                urlString += ";"
            }

            binding.sosKirimSms.setOnClickListener {
                val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(urlString))
                smsIntent.putExtra(
                    "sms_body",
                    "Halo, aku sedang dalam keadaan darurat! Aku butuh bantuanmu segera.\n\n________\nIni merupakan pesan otomatis dari Layanan Novia. Jika kamu mengenal pemilik nomor ini, mohon untuk menghubungi kembali atau segera ambil tindakan siaga."
                )
                startActivity(smsIntent)
            }
            binding.sosKirimSmsMessage.setOnClickListener {
                val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(urlString))
                smsIntent.putExtra(
                    "sms_body",
                    "Halo, aku sedang dalam keadaan darurat! Aku butuh bantuanmu segera.\n\n________\nIni merupakan pesan otomatis dari Layanan Novia. Jika kamu mengenal pemilik nomor ini, mohon untuk menghubungi kembali atau segera ambil tindakan siaga."
                )
                startActivity(smsIntent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}