package com.example.app.ui.api.models


var JWT : JwtResponse? = null

data class JwtResponse(
    var accessToken: String?,
    var id: String?,
    var username: String?,
    var email: String?,
    var roles: List<String?>
)