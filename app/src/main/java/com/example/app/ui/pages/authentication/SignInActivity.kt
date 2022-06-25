package com.example.app.ui.pages.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.R
import com.example.app.ui.MainActivity
import com.example.app.ui.api.ServiceBuilder
import com.example.app.ui.api.ApiService
import com.example.app.ui.api.USE_API
import com.example.app.ui.api.addProductShoppingCart
import com.example.app.ui.api.models.AuthManager
import com.example.app.ui.api.models.LoginRequest
import com.example.app.ui.api.models.JWT
import com.example.app.ui.api.models.LIST_PRODUCT_SHOPPING_CART
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection.HTTP_OK

/**
 * Activity SignIn page
 */
class SignInActivity : AppCompatActivity(R.layout.activity_sign_in) {

    // The email address
    private lateinit var emailAddress: EditText
    // The password
    private lateinit var password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailAddress = findViewById<EditText>(R.id.emailAddress)
        password = findViewById<EditText>(R.id.password)

        val tvInscription = findViewById<TextView>(R.id.tvInscription)
        tvInscription.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        val btnConnexion = findViewById<Button>(R.id.btnConnexion)
        btnConnexion.setOnClickListener {
            verifyEmail()
        }

    }

    /**
     * Verify if the email is correct
     * and allow to create a new user
     */
    private fun verifyEmail() {
        if (isEmailValid(emailAddress)) {
            signIn(this)
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
     *
     * @param activity the current activity
     */
    private fun signIn(activity: Activity) {
        //initiate the service

        CoroutineScope(Dispatchers.IO).launch {

            val res = USE_API.signIn(
                LoginRequest(emailAddress.text.toString(), password.text.toString())
            )

            if (res?.code() == HTTP_OK) {
                JWT = res?.body()!!
                JWT?.accessToken?.let { AuthManager.saveToken(it, activity) }

                if (LIST_PRODUCT_SHOPPING_CART.size != 0){
                    for (product in LIST_PRODUCT_SHOPPING_CART) {
                        addProductShoppingCart(product, null)
                    }
                }
                startActivity(Intent(applicationContext, MainActivity::class.java));
            }
        }
    }
}