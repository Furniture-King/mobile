package com.example.app.ui.api.models

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.example.app.ui.pages.home.HomeFragment
import kotlinx.coroutines.CoroutineScope

class AuthManager {
    companion object {
        fun getToken(act: FragmentActivity): String? {
            val prefs = act.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            return prefs.getString("token", "noToken")
        }

        fun saveToken(token: String, act: Activity) {
            val prefs: SharedPreferences =
                act.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val edit: SharedPreferences.Editor = prefs.edit()
            edit.putString("token", token)
            edit.apply()
        }

        fun disconnect(act: Activity) {
            val prefs = act.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val edit: SharedPreferences.Editor = prefs.edit()
            edit.remove("token")
            edit.apply()
        }

        fun isLogged(act: FragmentActivity): Boolean {
            return getToken(act) != "noToken"
        }

        fun tokenSyntax(act: FragmentActivity): String {
            return "Bearer " + getToken(act)
        }
    }
}