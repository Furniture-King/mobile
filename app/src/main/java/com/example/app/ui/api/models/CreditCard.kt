package com.example.app.ui.api.models

import java.io.Serializable



/**
 * Meaning of a credit card
 */
data class CreditCard (
    var id: String,
    var cardNumber: String?,
    val holder: String?,
    val expiry: String?,
    val cvv: String?,
): Serializable
