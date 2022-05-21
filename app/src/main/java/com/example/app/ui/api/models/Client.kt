package com.example.app.ui.api.models


import android.os.Parcel
import android.os.Parcelable
import java.util.*
import org.bson.types.ObjectId
import java.io.Serializable


var client: Client? = null;
var clientTest = Client(
    ObjectId("62794e36099815f27501623d"),
    1,
    "test8@test.test",
    "$2a$12\$meY5oZ.xwJEz3HzGjsA7l.",
    "$2a$12\$meY5oZ.xwJEz3HzGjsA7l.Nbjw7eBNwF1n9D9C0RM6pREoOFftJFO",
    1,
    "De Riv",
    "Geralt",
    "607070910",
    null,
    2,
    null,
    null
)


//data class Client(var newEmail: String, var password: String) : Serializable {
//    var id: ObjectId? = null
//    var status: Int? = null
//    var email: String? = newEmail
//    var passwordHash: String? = password
//    var passwordSalt: String? = null
//    var civility: Int? = null
//    var lastName: String? = null
//    var firstName: String? = null
//    var phone: String? = null
//    var favProduct: Array<Product>? = null
//    var nbConnection: Int? = null
//    var createdAt: Date? = null
//    var updatedAt: Date? = null
//
//}

data class Client(
    var id: ObjectId?,
    var status: Int?,
    var email: String?,
    var passwordHash: String?,
    var passwordSalt: String?,
    var civility: Int?,
    var lastName: String?,
    var firstName: String?,
    var phone: String?,
    var favProduct: Array<Product>?,
    var nbConnection: Int?,
    var createdAt: Date?,
    var updatedAt: Date?,
) : Serializable