package com.andridm.kampusapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.TextView

class SplashScreenActivity2 : AppCompatActivity() {
    var i=50
    var progressBar : ProgressBar? = null
    lateinit var progressText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen2)

        progressText = findViewById(R.id.tvBar)
        progressBar = findViewById(R.id.determinateBar)
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (i <= 100) {
                    progressText.setText(""+i)
                    progressBar?.setProgress(i)
                    i++
                    handler.postDelayed(this, 36)

                } else {
                    startActivity(Intent(this@SplashScreenActivity2,
                        MainActivity::class.java))
                    finish()
                }
            }
        },36)

    }
}