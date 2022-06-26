package com.example.app.ui.util

import android.content.Context
import android.widget.Toast

/**
 * warned a user that this function is not implemented yet
 */
fun notAvailableYet(context: Context) {
    Toast.makeText(
        context,
        "Bientôt disponible \uD83D\uDE09",
        Toast.LENGTH_SHORT
    ).show()
}