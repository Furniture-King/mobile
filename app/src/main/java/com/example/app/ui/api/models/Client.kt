package com.example.app.ui.api.models


import android.os.Parcel
import android.os.Parcelable
import java.util.*
import org.bson.types.ObjectId
import java.io.Serializable


var user: Client? = null;

data class Client(
    var id: ObjectId?,
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