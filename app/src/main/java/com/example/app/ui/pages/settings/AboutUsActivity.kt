package com.example.app.ui.pages.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.databinding.ActivityAboutUsBinding

/**
 * Activity AboutUs
 */
class AboutUsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}