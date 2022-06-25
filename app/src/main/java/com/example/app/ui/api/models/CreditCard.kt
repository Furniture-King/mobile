package com.example.app.ui.api.models

import java.io.Serializable

// The  credit card list
var LIST_CREDIT_CARD = mutableListOf<CreditCard>()
// The credit card ID EXTRA
var CREDIT_CARD_ID_EXTRA = "CreditCardExtra"

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
