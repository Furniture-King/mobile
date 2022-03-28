package com.example.app.ui.pages.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.R
import com.example.app.ui.pages.MainActivity

/**
 * Page de connexion
 */
class SignInPage : AppCompatActivity() {

    private lateinit var emailAddress: EditText
    private lateinit var password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        // get reference to all views
        emailAddress = findViewById<EditText>(R.id.emailAddress)
        password = findViewById<EditText>(R.id.password)

        val connexionButton = findViewById<Button>(R.id.connexionButton)
        connexionButton.setOnClickListener {
            auth()
        }
        signIn()
    }

    private fun auth() {
        if (!isEmailValid(emailAddress)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            Toast.makeText(
                applicationContext, "Bienvenue King",
                Toast.LENGTH_SHORT
            ).show()

            // TODO Authentification depuis l'API du bro !

        } else {
            Toast.makeText(
                applicationContext, "Invalid email address",
                Toast.LENGTH_SHORT
            ).show()
            clearEditText(arrayOf(emailAddress, password))
        }
    }

    fun signIn() {
        val inscription = findViewById<TextView>(R.id.tvInscription)
        inscription.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }
    }

//    fun isEmailValid(): Boolean {
//        if (!EMAIL_REGEX.toRegex().matches(emailAddress.text)) {
//            Toast.makeText(
//                applicationContext, "Invalid email address",
//                Toast.LENGTH_SHORT
//            ).show()
//            return false
//        }
//        Toast.makeText(
//            applicationContext, "Bienvenue King",
//            Toast.LENGTH_SHORT
//        ).show()
//        return true
//    }
}