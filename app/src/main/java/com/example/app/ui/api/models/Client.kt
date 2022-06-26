package com.example.app.ui.api.models


import java.io.Serializable

/**
 * Meaning of a user
 */
data class Client(
    var id: String?,
    var role: String?,
    var email: String?,
    var passwordHash: String?,
    var passwordSalt: String?,
    var civility: Int?,
    var lastName: String?,
    var firstName: String?,
    var address: String?,
    var postalCode: String?,
    var city: String?,
    var phone: String?,
    var nbConnection: Int?,
    var createdAt: Long?,
    var updatedAt: Long?,
    ) : Serializable