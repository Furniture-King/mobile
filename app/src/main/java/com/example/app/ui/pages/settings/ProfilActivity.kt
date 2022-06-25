package com.example.app.ui.pages.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.databinding.ActivityProfilBinding
import com.example.app.ui.MainActivity
import com.example.app.ui.api.ApiService
import com.example.app.ui.api.ServiceBuilder
import com.example.app.ui.api.models.Client
import com.example.app.ui.api.models.user
import com.example.app.ui.pages.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
//            updateClient()
            finish()
        }
    }
//
//    private fun updateClient() {
//        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
//        user?.lastName=binding.lastName.toString()
//        user?.firstName=binding.firstName.toString()
//        user?.passwordHash=binding.password.text.toString()
//        user?.address=binding.address.text.toString()
//        user?.phone=binding.phone.text.toString()
//
//
//        destinationService.updateProfile(user).enqueue(object : Callback<Client> {
//            override fun onResponse(call: Call<Client>, response: Response<Client>) {
//                Log.d("LOGIN RESPONSE", "${response.body()}")
//                if (response.isSuccessful) {
//                    val client = response.body()
//                    Log.d("CLIENT", "client : ${client}")
//                    user = client
//                    finish();
//                    startActivity(Intent(applicationContext, MainActivity::class.java));
//                } else {
//                    Toast.makeText(
//                        applicationContext,
//                        "Something went wrong ${response.message()}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Client>, t: Throwable) {
//                Toast.makeText(
//                    applicationContext,
//                    "Something went wrong $t",
//                    Toast.LENGTH_SHORT
//                ).show()
//                Log.d("LOGIN password", "$t")
//
//            }
//        })
//
//    }
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