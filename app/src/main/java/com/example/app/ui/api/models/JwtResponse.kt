package com.example.app.ui.api.models




/**
 * Meaning of a jwt response
 */
data class JwtResponse(
    var accessToken: String?,
    var id: String?,
    var username: String?,
    var email: String?,
    var roles: List<String?>
)