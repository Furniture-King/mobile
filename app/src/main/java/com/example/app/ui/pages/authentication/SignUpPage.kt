package com.example.app.ui.pages.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.R
import com.example.app.ui.api.ApiService
import com.example.app.ui.api.ServiceBuilder
import com.example.app.ui.api.models.Client

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Sign Up Page
 */
class SignUpPage : AppCompatActivity() {

    private lateinit var emailAddress: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailAddress = findViewById(R.id.emailAddress)
        confirmPassword = findViewById(R.id.confirmPassword)
        password = findViewById(R.id.password)


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

    /**
     * Verify if the email and password are correct
     *
     * @return : boolean
     */
    private fun verifyEmailPassword(): Boolean {
        return isPassWordValid(password, confirmPassword) && isEmailValid(emailAddress)
    }

    /**
     * Allow to create a new user
     */
    private fun signUp() {
        if (verifyEmailPassword()) {
//            Log.d(
//                "TAG",
//                "\nverify is true"
//            )
            saveNewUser()
            finish()
        } else {
            Toast.makeText(
                this@SignUpPage.applicationContext,
                "Mot de passe ou email invalide !",
                Toast.LENGTH_SHORT
            ).show()
            clearEditText(arrayOf(emailAddress, password, confirmPassword))
        }
    }

    /**
     * Allow to register a new user
     */
    private fun saveNewUser() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
        val client: Client = Client(
            null,
            null,
            emailAddress.text.toString(),
            password.text.toString(),
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val test = destinationService.signUp(client).enqueue(object : Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val client = response.body()!!
                    Log.d("CLIENT", "client : ${client}")
                    val homeFragment = this
                } else {
                    Toast.makeText(
                        this@SignUpPage.applicationContext,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Toast.makeText(
                    this@SignUpPage.applicationContext,
                    "Something went wrong $t",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }
        })
        Log.d("test",test.toString()+"")
    }

}