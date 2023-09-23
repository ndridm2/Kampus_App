package com.andridm.kampusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val data = intent.getParcelableExtra<Kampus>("DATA")
        data?.photo?.let { findViewById<ImageView>(R.id.img_item_photo).setImageResource(it) }
        findViewById<TextView>(R.id.tv_item_name).text = data?.name
        findViewById<TextView>(R.id.tv_item_description).text = data?.description

    }
}