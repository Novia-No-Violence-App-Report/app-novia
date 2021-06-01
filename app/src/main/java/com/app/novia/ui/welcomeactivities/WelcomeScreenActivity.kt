package com.app.novia.ui.welcomeactivities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.app.novia.R
import com.app.novia.databinding.ActivityWelcomeScreenBinding
import com.app.novia.ui.login.LoginActivity
import com.app.novia.ui.mainactivity.MainActivity
import com.app.novia.ui.registeractivity.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import me.relex.circleindicator.CircleIndicator3

class WelcomeScreenActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private var titlesList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    private val binding: ActivityWelcomeScreenBinding by lazy {
        ActivityWelcomeScreenBinding.inflate(
            layoutInflater
        )
    }

    private fun addToList(title: String, image: Int) {
        titlesList.add(title)
        imagesList.add(image)
    }

    private fun postToList() {
        for (i in 1..3) {
            addToList("Description $i", R.mipmap.ic_launcher_round)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        postToList()
        binding.viewPager.adapter = ViewPagerAdapter(titlesList, imagesList)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //INDICATOR
        //val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        //indicator.setViewPager(viewPager)

        with(binding) {
            btnLoginPage.setOnClickListener {
                val intent = Intent(this@WelcomeScreenActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            btnRegisterPage.setOnClickListener {
                val intent = Intent(this@WelcomeScreenActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@WelcomeScreenActivity, MainActivity::class.java).also { intent ->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

}