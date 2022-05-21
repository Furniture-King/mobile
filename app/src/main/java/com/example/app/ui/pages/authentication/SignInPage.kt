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
import com.example.app.ui.api.models.clientTest
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
            val intent = Intent(this.applicationContext, SignUpPage::class.java)
            startActivity(intent)
        }
        val connexionButton = findViewById<Button>(R.id.connexionButton)
        connexionButton.setOnClickListener {
            auth()
        }
//        signIn()
    }

    private fun auth() {
//        client = Client( null,"Justin", emailAddress.toString(),null,null,null)
//        Log.d("client", "${client}")
//        Log.d("client?.name", "${client?.name}")


        if (!isEmailValid(emailAddress)) {
            // TODO Authentification depuis l'API du bro !

            getClient()
//            getAllClient()
            if (true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                Toast.makeText(
                    applicationContext, "Bienvenue King",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext, "Something went wrong King",
                    Toast.LENGTH_SHORT
                ).show()
            }


        } else {
            Toast.makeText(
                applicationContext, "Invalid email address",
                Toast.LENGTH_SHORT
            ).show()
            clearEditText(arrayOf(emailAddress, password))
        }
    }

//    private fun getAllClient() {
//        //initiate the service
//        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
//        val requestCall = destinationService.getClientList()
//        //make network call asynchronously
//        requestCall.enqueue(object : Callback<List<Client>> {
//            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
//                Log.d("allClients", "onResponse: ${response.body()}")
//                if (response.isSuccessful) {
//                    val allClients = response.body()!!.toMutableList()
//                    Log.d("allClients", "allClients size : ${allClients.size}")
//
//                } else {
//                    Toast.makeText(
//                        this@SignInPage.applicationContext,
//                        "Something went wrong ${response.message()}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Log.d("allClients", "Something went wrong : ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
//                Toast.makeText(
//                    this@SignInPage.applicationContext,
//                    "Something went wrong $t",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
//                Log.d("allClients", "Something went wrong : $t")
//
//            }
//        })
//    }


//    fun signIn() {
//        val inscription = findViewById<TextView>(R.id.tvInscription)
//        inscription.setOnClickListener {
//            val intent = Intent(this, SignUpPage::class.java)
//            startActivity(intent)
//        }
//    }

    fun getClient() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = destinationService.signIn(clientTest)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<Client> {
            override fun onResponse(call: Call<Client>, response: Response<Client>) {
                Log.d("client", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val client = response.body()!!
                    Log.d("client", "allClients size : ${client}")
                } else {
                    Toast.makeText(
                        this@SignInPage.applicationContext,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("client", "Something went wrong : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Client>, t: Throwable) {
                Toast.makeText(
                    this@SignInPage.applicationContext,
                    "Something went wrong $t",
                    Toast.LENGTH_SHORT
                )
                    .show()
                Log.d("client", "Something went wrong : $t")

            }
        })
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