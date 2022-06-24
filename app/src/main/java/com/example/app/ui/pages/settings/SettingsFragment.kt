package com.example.app.ui.pages.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.databinding.FragmentSettingsBinding
import com.example.app.ui.MainActivity
import com.example.app.ui.api.models.*
import com.example.app.ui.pages.authentication.SignInActivity


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userProfil: TextView = binding.tvUserProfil
        userProfil.setOnClickListener {

            if (activity?.let { AuthManager.isLogged(it) } == true) {
                Log.d("jwtResponse",JWT?.id.toString())
                if (JWT?.id == null) {
                    startActivity(Intent(context, SignInActivity::class.java));
                } else {
                    startActivity(Intent(context, ProfilActivity::class.java))
                }
            }
        }
        val tvAbout: TextView = binding.tvAbout
        tvAbout.setOnClickListener {
            if (activity?.let { AuthManager.isLogged(it) } == true) {
                if (JWT?.id == null) {
                    startActivity(Intent(context, SignInActivity::class.java));
                } else {
                    startActivity(Intent(context, AboutUsActivity::class.java))
                }
            }
        }

        val tvPayment: TextView = binding.tvPayment
        tvPayment.setOnClickListener {
            if (activity?.let { AuthManager.isLogged(it) } == true) {
                if (JWT?.id == null) {
                    startActivity(Intent(context, SignInActivity::class.java));
                } else {
                    startActivity(Intent(context, PaymentActivity::class.java))
                }
            }
        }

        val logout: Button = binding.logout
        logout.setOnClickListener {
            logout()
            if (activity?.let { AuthManager.isLogged(it) } == true) {
                if (JWT?.id == null) {
                    startActivity(Intent(context, SignInActivity::class.java));
                } else {
                    startActivity(Intent(context, MainActivity::class.java))
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun logout() {
        user = null
        JWT = null
        SHOPPING_CART = null
        LIST_PRODUCT_SHOPPING_CART = mutableListOf()
        LIST_ALL_PRODUCT = mutableListOf()
        LIST_PRODUCT_FAVOURITE = mutableListOf()
        TOTAL_PRICE_SHOPPING_CART = 0f
        LIST_CREDIT_CARD = mutableListOf()
        AuthManager.disconnect(requireActivity())
        activity?.finish()
        startActivity(Intent(context, MainActivity::class.java));

    }
}


