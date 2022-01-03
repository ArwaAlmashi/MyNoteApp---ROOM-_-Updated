package com.example.mynoteapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.mynoteapp.databinding.ActivityGetStartedBinding
import com.example.mynoteapp.lightStatueBar
import com.example.mynoteapp.setFullScreen
import java.net.URL


class GetStartedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreen(window)
        lightStatueBar(window)

        Glide.with(this)
            .load(URL("https://i.pinimg.com/originals/c2/a1/1d/c2a11dae4a9e153f1d01a12107ca3912.gif"))
            .into(binding.welcomeGirlImage)

        binding.getStartedButton.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }

    }
}