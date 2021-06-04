package com.app.novia

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.novia.core.domain.model.Article
import com.app.novia.databinding.ActivityNewsBinding
import com.bumptech.glide.Glide
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewsActivity : AppCompatActivity() {
    private lateinit var newsSelected: Article
    private val binding: ActivityNewsBinding by lazy {
        ActivityNewsBinding.inflate(
            layoutInflater
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        newsSelected = intent.getSerializableExtra("ARTICLE") as Article


        with(binding) {
            Glide.with(applicationContext)
                .load(newsSelected.imageUrl)
                .centerCrop()
                .into(activitynewsImageview)

            activitynewsJudulTextview.text = newsSelected.title
            activitynewsSourceTextview.text = StringBuilder("${newsSelected.author} - ${newsSelected.source?.name}")

            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date: Date = dateFormat.parse(newsSelected.date)
            val formatter: DateFormat = SimpleDateFormat("dd MMM yyyy")
            val dateStr: String = formatter.format(date)

            activitynewsDateTextview.text = dateStr
            activitynewsContentTextview.text = newsSelected.content!!.substring(0, 201)
            activitynewsBacaButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsSelected.url)))
            }

            binding.newsBackbuttonImageview.setOnClickListener {
                finish()
            }
        }
    }
}