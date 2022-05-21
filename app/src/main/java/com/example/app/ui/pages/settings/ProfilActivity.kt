package com.example.app.ui.pages.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.databinding.ActivityProfilBinding
import com.example.app.ui.api.models.clientTest
import com.example.app.ui.pages.home.HomeFragment


class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClientProfil()
        saveClientProfil()
    }

    private fun saveClientProfil() {
        binding.save.setOnClickListener {
            val intent = Intent(this.applicationContext, HomeFragment::class.java)
            startActivity(intent)
        }
    }

    private fun setClientProfil() {
        binding.lastName.setText(clientTest.lastName)
        binding.firstName.setText(clientTest.firstName)
        binding.emailAddress.setText(clientTest.email)
        binding.phone.setText(clientTest.phone)
        binding.physicalAddress.setText("Adresse provisoire")

    }
}