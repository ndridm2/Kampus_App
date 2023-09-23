package com.andridm.kampusapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.andridm.kampusapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardView.setOnClickListener{
            val anime_1 = ObjectAnimator.ofFloat(binding.cardView,
                "scaleX", 1f, 0f)
            val anime_2 = ObjectAnimator.ofFloat(binding.cardView,
                "scaleX", 0f, 1f)

            anime_1.interpolator = DecelerateInterpolator()
            anime_2.interpolator = AccelerateInterpolator()

            anime_1.addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    binding.cardView
                    binding.imageView.setImageResource(R.drawable.dicoding)
                    binding.tvName.text = "Dicoding"
                    binding.tvEmail.text = "Indonesia"
                    anime_2.start()
                }
            })
            anime_1.start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menubar_about, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> startActivity(Intent(
                this@AboutActivity, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}