package com.example.app.ui.api.models


import android.os.Parcel
import android.os.Parcelable
import java.util.*
import java.io.Serializable

// The user
var user: Client? = null;

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
    var favProduct: List<String>?,
    var createdAt: Long?,
    var updatedAt: Long?,
    ) : Serializable