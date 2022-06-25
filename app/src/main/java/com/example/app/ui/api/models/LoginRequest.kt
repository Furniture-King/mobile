package com.example.app.ui.api.models


/**
 * Meaning of a login request
 */
data class LoginRequest (
    var email: String,
    var password: String
)