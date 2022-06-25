package com.example.app.ui.pages.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.R
import com.example.app.ui.api.ApiService
import com.example.app.ui.api.ServiceBuilder
import com.example.app.ui.api.models.LoginRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection.HTTP_OK

/**
 * Sign Up Page
 */
class SignUpActivity : AppCompatActivity(R.layout.activity_sign_up) {

    // The email address
    private var emailAddress: EditText = findViewById(R.id.emailAddress)
    // The password
    private var password: EditText = findViewById(R.id.password)
    // The confirm password
    private var confirmPassword: EditText = findViewById(R.id.confirmPassword)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inscription = findViewById<TextView>(R.id.tvConnexion)
        inscription.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        val inscriptionButton = findViewById<Button>(R.id.inscriptionButton)
        inscriptionButton.setOnClickListener {
            verifyEmailPassword()
        }
    }

    /**
     * Verify if the email and password are correct
     * and allow to create a new user
     */
    private fun verifyEmailPassword() {
        if (isPassWordValid(password, confirmPassword) && isEmailValid(emailAddress)) {
            signUp()
            finish()
            Toast.makeText(
                applicationContext,
                "Bienvenue King !",
                Toast.LENGTH_SHORT
            ).show()
            clearEditText(arrayOf(emailAddress, password, confirmPassword))
        } else {
            Toast.makeText(
                applicationContext,
                "Mot de passe ou email invalide !",
                Toast.LENGTH_SHORT
            ).show()
            clearEditText(arrayOf(emailAddress, password, confirmPassword))
        }
    }

    /**
     * Allow to register a new user
     */
    private fun signUp() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            val res = destinationService.signUp(
                LoginRequest(emailAddress.text.toString(), password.text.toString())
            )

            if (res?.code() == HTTP_OK) {
                finish()
            }

        }
    }

}