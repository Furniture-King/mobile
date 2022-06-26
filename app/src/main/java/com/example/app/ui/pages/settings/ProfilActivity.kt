package com.example.app.ui.pages.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.databinding.ActivityProfilBinding
import com.example.app.ui.user
import com.example.app.ui.util.notAvailableYet

/**
 * Profil Page
 *
 * Allow the user to change is personal data
 */
class ProfilActivity : AppCompatActivity() {

    // Link this activity to the view xml
    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClientProfil()
        saveClientProfil()
    }

    /**
     * Save all the user data
     */
    private fun saveClientProfil() {
        binding.btnSave.setOnClickListener {
            notAvailableYet(this.applicationContext)
            finish()
        }
    }

    /**
     * Completed the form user
     */
    private fun setClientProfil() {
        binding.lastName.setText(user?.lastName)
        binding.firstName.setText(user?.firstName)
        binding.emailAddress.setText(user?.email)
        binding.phone.setText(user?.phone)
        binding.address.setText(user?.address)

    }
}