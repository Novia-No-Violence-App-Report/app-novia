package com.app.novia.ui.contactlist

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.R
import com.app.novia.core.domain.model.EmergencyContactEntity
import com.app.novia.core.ui.ContactListAdapter
import com.app.novia.databinding.ActivityContactListBinding
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListActivity : AppCompatActivity() {

    private lateinit var contactDialog: Dialog
    private var state = 0 // 1=Adding, 2=Editing, 3=Deleting
    private lateinit var currentContact: EmergencyContactEntity
    private val viewModel: ContactListViewModel by viewModel()
    private val adapter by lazy { ContactListAdapter() }

    private val submitBtn: Button by lazy { contactDialog.findViewById(R.id.dialog_submit_contact) }
    private val dialogTitle: TextView by lazy { contactDialog.findViewById(R.id.dialog_add_contact_title) }
    private val nameEditText: EditText by lazy { contactDialog.findViewById(R.id.dialog_add_contact_name) }
    private val phoneEditText: EditText by lazy { contactDialog.findViewById(R.id.dialog_add_contact_phone) }

    private val binding: ActivityContactListBinding by lazy {
        ActivityContactListBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAddContactDialog()
        initViews()
        initObservers()
        initOnclickListeners()
    }

    private fun initViews() {
        with(binding) {
            contactListListview.adapter = adapter
            adapter.setOnDeleteCallback(object : ContactListAdapter.OnDeleteClickCallback {
                override fun onDeleteContactClicked(
                    contactEntity: EmergencyContactEntity?,
                    position: Int
                ) {
                    state = 3
                    if (contactEntity != null) {
                        currentContact = contactEntity
                        transaction()
                    }
                }
            })
            adapter.setOnEditCallback(object : ContactListAdapter.OnEditClickCallback {
                override fun onEditContactClicked(
                    contactEntity: EmergencyContactEntity?,
                    position: Int
                ) {
                    state = 2
                    dialogTitle.text = getString(R.string.edit_contact)
                    if (contactEntity != null) {
                        nameEditText.text =
                            Editable.Factory.getInstance().newEditable(contactEntity.name)

                        phoneEditText.text =
                            Editable.Factory.getInstance().newEditable(contactEntity.phoneNumber)
                        currentContact = contactEntity
                        contactDialog.show()
                    }
                }
            })
        }

    }

    private fun initObservers() {
        viewModel.getEmergencyContacts().observe(this, {
            adapter.setData(it as ArrayList<EmergencyContactEntity>)
            adapter.closeAllItems()
        })
    }

    private fun initOnclickListeners() {
        binding.contactListBtnBack.setOnClickListener {
            finish()
        }
        binding.contactListFabAdd.setOnClickListener {
            state = 1
            nameEditText.text.clear()
            phoneEditText.text.clear()
            dialogTitle.text = getString(R.string.add_contact)
            contactDialog.show()
        }

        submitBtn.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (state == 1) {
                currentContact = EmergencyContactEntity(name, phone)
            }
            else if (state == 2) {
                currentContact.name = name
                currentContact.phoneNumber = phone
            }
            transaction()
            nameEditText.text.clear()
            phoneEditText.text.clear()
            contactDialog.dismiss()
        }
    }

    private fun transaction() {
        var msg = "Kontak berhasil "
        runBlocking {
            when (state) {
                1 -> {
                    msg += "ditambahkan"
                    viewModel.addEmergencyContact(currentContact)
                }
                2 -> {
                    msg += "diperbarui"
                    viewModel.updateEmergencyContact(currentContact)
                }
                3 -> {
                    msg += "dihapus"
                    viewModel.deleteEmergencyContact(currentContact)
                }
            }
            Toast.makeText(
                applicationContext, msg,
                Toast.LENGTH_LONG
            ).show()
            state = 0
        }
    }

    private fun initAddContactDialog() {
        contactDialog = Dialog(this)
        contactDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        contactDialog.setContentView(R.layout.dialog_add_contact)
    }
}