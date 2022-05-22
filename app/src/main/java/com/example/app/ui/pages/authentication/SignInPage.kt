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
import com.example.app.ui.api.ServiceBuilder
import com.example.app.ui.api.models.Client
import com.example.app.ui.api.models.user
import com.example.app.ui.api.ApiService
import com.example.app.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


        val tvInscription = findViewById<TextView>(R.id.tvInscription)
        tvInscription.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }
        val btnConnexion = findViewById<Button>(R.id.btnConnexion)
        btnConnexion.setOnClickListener {
            signIn()
        }

    }

    /**
     * Verify if the email is correct
     *
     * @return : boolean
     */
    private fun verifyEmailPassword(): Boolean {
        return isEmailValid(emailAddress)
    }

    /**
     * Allow to create a new user
     */
    private fun signIn() {
        if (verifyEmailPassword()) {
            LogIn()
//            finish()
        } else {
            Toast.makeText(
                applicationContext,
                "Mot de passe ou email invalide !",
                Toast.LENGTH_SHORT
            ).show()
            clearEditText(arrayOf(emailAddress, password))
        }
    }

    /**
     * Allow to register a new user
     */
    private fun LogIn() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)


        val client = Client(
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
            null,
            null,
            null,
            null
        )
        Log.d("LOGIN emailAddress", "${emailAddress.text}")
        Log.d("LOGIN password", "${password.text}")

        destinationService.signIn(client).enqueue(object : Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                Log.d("LOGIN RESPONSE", "${response.body()}")
                if (response.isSuccessful) {
                    val client = response.body()
                    Log.d("CLIENT", "client : ${client}")
                    user = client
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Something went wrong $t",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("LOGIN password", "$t")

            }
        })
//        Log.d("test",test.toString()+"")
    }

}