package com.example.app.ui.api.models


// The jwt response
var JWT : JwtResponse? = null

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