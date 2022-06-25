package com.example.app.ui.pages.authentication

import android.widget.EditText

// REGEX of a email
private var EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
// Regex for password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters.
private var PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}"

/**
 * Check if the email is valid
 *
 * @param emailAddress a input text
 * @return true or false
 */
fun isEmailValid(emailAddress: EditText): Boolean {
    return EMAIL_REGEX.toRegex().matches(emailAddress.text)
}

/**
 * Check if the password is valid with the confirm password
 *
 * @param password the password
 * @param confirmPassword the confirm password
 * @return true or false
 */
fun isPassWordValid(password: EditText, confirmPassword: EditText): Boolean {
    return ((PASSWORD_REGEX.toRegex()
        .matches(password.text)) && (password.text.toString() == confirmPassword.text.toString()))
}

/**
 * Allow to clear an input text
 *
 * @param elementEditText a list of input text
 */
fun clearEditText(elementEditText: Array<EditText>) {
    elementEditText.forEach { e -> e.setText("") }
}