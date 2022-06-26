package com.example.app.ui.pages.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.R
import com.example.app.ui.api.USE_API
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
    private lateinit var emailAddress: EditText
    // The password
    private lateinit var password: EditText
    // The confirm password
    private lateinit var confirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailAddress = findViewById(R.id.emailAddress)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
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
                "Votre compte à bien été enregistré :)",
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
        CoroutineScope(Dispatchers.IO).launch {

            val res = USE_API.signUp(
                LoginRequest(emailAddress.text.toString(), password.text.toString())
            )

            if (res?.code() == HTTP_OK) {
                finish()
            }

        }
    }

}