package com.example.app.ui.pages.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.R
import com.example.app.ui.JWT
import com.example.app.ui.LIST_PRODUCT_FAVOURITE
import com.example.app.ui.LIST_PRODUCT_SHOPPING_CART
import com.example.app.ui.MainActivity
import com.example.app.ui.api.USE_API
import com.example.app.ui.api.addProductBookmark
import com.example.app.ui.api.addProductShoppingCart
import com.example.app.ui.api.models.AuthManager
import com.example.app.ui.api.models.LoginRequest
import kotlinx.coroutines.*
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

                // Add
                if (LIST_PRODUCT_SHOPPING_CART.size != 0) {
                    for (product in LIST_PRODUCT_SHOPPING_CART) {
                        GlobalScope.launch {
                            //You can use for background procces
                            withContext(Dispatchers.Main) {
                                addProductShoppingCart(product, null)
                            }
                        }
                    }
                }
                if (LIST_PRODUCT_FAVOURITE.size != 0) {
                    for (product in LIST_PRODUCT_FAVOURITE) {
                        GlobalScope.launch {
                            //You can use for background procces
                            withContext(Dispatchers.Main) {
                                addProductBookmark(product, null)
                            }
                        }
                    }
                }
                startActivity(Intent(applicationContext, MainActivity::class.java));
            }
        }
    }
}