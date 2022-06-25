package com.example.app.ui.api.models

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.example.app.ui.pages.home.HomeFragment
import kotlinx.coroutines.CoroutineScope

/**
 * Auth manager
 *
 * Manage all manipulation with the user token
 */
class AuthManager {
    companion object {
        /**
         *  Get the user token
         *
         *  @param act a FragmentActivity
         *  @return null or a string
         */
        fun getToken(act: FragmentActivity): String? {
            val prefs = act.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            return prefs.getString("token", "noToken")
        }

        /**
         * Save the user token
         *
         * @param token the string token of a user
         * @param act null a Activity
         */
        fun saveToken(token: String, act: Activity) {
            val prefs: SharedPreferences =
                act.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val edit: SharedPreferences.Editor = prefs.edit()
            edit.putString("token", token)
            edit.apply()
        }

        /**
         * Allow to remove the user token
         *
         * @param act a Activity
         */
        fun disconnect(act: Activity) {
            val prefs = act.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val edit: SharedPreferences.Editor = prefs.edit()
            edit.remove("token")
            edit.apply()
        }

        /**
         * Check if the token is already save
         *
         * @param act a FragmentActivity
         * @return true or false
         */
        fun isLogged(act: FragmentActivity): Boolean {
            return getToken(act) != "noToken"
        }
    }
}