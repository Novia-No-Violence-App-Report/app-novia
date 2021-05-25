package com.app.novia.ui.contactlist

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.R
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.databinding.ActivityContactListBinding
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListActivity : AppCompatActivity() {

    private lateinit var addContactDialog: Dialog
    private val viewModel : ContactListViewModel by viewModel()
    private val binding: ActivityContactListBinding by lazy {
        ActivityContactListBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAddContactDialog()
        initObservers()
        initOnclickListeners()
    }

    private fun initObservers() {
        viewModel.getEmergencyContacts().observe(this, {
            binding.contactListTextTest.text = it.toString()
        })
    }

    private fun initOnclickListeners() {
        binding.contactListBtnBack.setOnClickListener {
            finish()
        }
        binding.contactListFabAdd.setOnClickListener {
            addContactDialog.show()
        }

        val submitBtn = addContactDialog.findViewById<Button>(R.id.dialog_submit_contact)
        val nameEditText = addContactDialog.findViewById<EditText>(R.id.dialog_add_contact_name)
        val phoneEditText = addContactDialog.findViewById<EditText>(R.id.dialog_add_contact_phone)

        submitBtn.setOnClickListener {
            val name = nameEditText.text
            val phone = phoneEditText.text
            runBlocking {
                viewModel.addEmergencyContact(EmergencyContactEntity( name.toString(), phone.toString()))
            }
            Toast.makeText(
                this, "Name: $name. Phone: $phone",
                Toast.LENGTH_LONG
            ).show()
            addContactDialog.dismiss()
        }
    }

    private fun initAddContactDialog() {
        addContactDialog = Dialog(this)
        addContactDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addContactDialog.setContentView(R.layout.dialog_add_contact)
    }
}