package com.example.materialme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var sportsTitle: TextView
    private lateinit var sportsNews : TextView
    private lateinit var sportsImage: ImageView

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_NEWS = "extra_news"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        sportsTitle = findViewById(R.id.title)
        sportsNews = findViewById(R.id.detailSubTitle)
        sportsImage = findViewById(R.id.detailSportsImage)

        sportsTitle.text = intent.getStringExtra(EXTRA_TITLE)
        sportsNews.text = intent.getStringExtra(EXTRA_NEWS)
        Glide.with(this).load(intent.getIntExtra(EXTRA_IMAGE, 0))
            .into(sportsImage)
    }
}