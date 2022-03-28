package com.example.app.ui.pages.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import app.R

/**
 * Sign Up Page
 */
class SignUpPage : AppCompatActivity() {

    private lateinit var emailAddress: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailAddress = findViewById<EditText>(R.id.emailAddress)
        confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        password = findViewById<EditText>(R.id.password)


        val inscription = findViewById<TextView>(R.id.tvConnexion)
        inscription.setOnClickListener {
            val intent = Intent(this, SignInPage::class.java)
            startActivity(intent)
        }
        val inscriptionButton = findViewById<Button>(R.id.inscriptionButton)
        inscriptionButton.setOnClickListener {
            signUp()
        }

    }

    private fun verify(): Boolean {
//        Log.d(
//            "TAG","test : "+isPassWordValid(password, confirmPassword))

        return isPassWordValid(password, confirmPassword) && isEmailValid(emailAddress)
    }

    private fun signUp() {


        if (verify()) {
//            Log.d(
//                "TAG",
//                "\nverify is true"
//            )
            val intent = Intent(this, SignInPage::class.java)
            startActivity(intent)

            // TODO Post new user !
        } else {
            clearEditText(arrayOf(emailAddress, password, confirmPassword))
        }

    }
}