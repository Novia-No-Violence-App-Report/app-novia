package com.app.novia.ui.settingsactivity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.databinding.ActivitySettingsBinding
import com.app.novia.ui.welcomeactivities.WelcomeScreenActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {
    private val viewModel: SettingsViewModel by viewModel()
    private val binding: ActivitySettingsBinding by lazy {
        ActivitySettingsBinding.inflate(
            layoutInflater
        )
    }

    private var user: FirebaseUser? = null
    private lateinit var filepath: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        viewModel.auth.currentUser.let {
            user = it
        }

        initializeClickListeners()
        initializeViews()
    }

    private fun initializeViews() {
        with(binding) {
            settingsUserName.text = user?.displayName
            settingsUserEmail.text = user?.email
        }
    }

    private fun initializeClickListeners() {
        with(binding) {
            settingsBtnBack.setOnClickListener {
                finish()
            }

            profilePic.setOnClickListener {
                startFileChooser()
            }

            btnLogout.setOnClickListener {
                viewModel.auth.signOut()
                Intent(this@SettingsActivity, WelcomeScreenActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
    }

    private fun startFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Choose picture"), 111)

        val storage = FirebaseStorage.getInstance().reference.child("images/pic.jpg")

        storage.putFile(filepath)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Foto berhasil diunggah!", Toast.LENGTH_LONG)
                    .show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Foto gagal diperbarui", Toast.LENGTH_LONG)
                    .show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            filepath = data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
            binding.profilePic.setImageBitmap(bitmap)
        }
    }

}