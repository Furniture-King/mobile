package com.example.app.ui.pages.authentication

import android.widget.EditText

/**
 * Page de connexion
 */
private var EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

private var PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}"
fun isEmailValid(emailAddress: EditText): Boolean {
    return EMAIL_REGEX.toRegex().matches(emailAddress.text)
}

fun isPassWordValid(password: EditText, confirmPassword: EditText): Boolean {
//    Log.d(
//        "TAG",
//        "\n\n psw != Cpsw : " + (password.text.toString() == confirmPassword.text.toString())
//    )
//    Log.d(
//        "TAG",
//        "\n\n REGEX : " + (PASSWORD_REGEX.toRegex()
//            .matches(password.text.toString()))
//    )
//
//    Log.d(
//        "TAG",
//        "\n\n res final : " + ((PASSWORD_REGEX.toRegex()
//            .matches(password.text)) && (password.text.toString() == confirmPassword.text.toString()))
//    )
    return ((PASSWORD_REGEX.toRegex()
        .matches(password.text)) && (password.text.toString() == confirmPassword.text.toString()))
}

fun clearEditText(elementEditText: Array<EditText>) {
    elementEditText.forEach { e -> e.setText("") }
}