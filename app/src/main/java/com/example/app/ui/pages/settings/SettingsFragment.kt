package com.example.app.ui.pages.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.databinding.FragmentSettingsBinding
import com.example.app.ui.api.models.client
import com.example.app.ui.MainActivity


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
            val intent = Intent(this.context, ProfilActivity::class.java)
            startActivity(intent)
        }
        val tvAbout: TextView = binding.tvAbout
        tvAbout.setOnClickListener {
            val intent = Intent(this.context, AboutUsActivity::class.java)
            startActivity(intent)
        }

        val tvPayment: TextView = binding.tvPayment
        tvPayment.setOnClickListener {
            val intent = Intent(this.context, PaymentActivity::class.java)
            startActivity(intent)
        }

        val logout: Button = binding.logout
        logout.setOnClickListener {
            logout()
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun logout() {
        client = null
    }
}


