package com.example.app.ui.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * ExpiryDateTextWatcher
 *
 * Allow to check and correctly display the expiry date of the credit card
 *
 * @param edtExpiryDate an input field
 */
class ExpiryDateTextWatcher(edtExpiryDate: EditText) : TextWatcher {
    var lastInput=""
    var edtExpiryDate = edtExpiryDate

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {
        val input = s.toString()
        val formatter = SimpleDateFormat("MM/yy", Locale.FRANCE);
        val expiryDateDate = Calendar.getInstance();
        try {
            expiryDateDate.setTime(formatter.parse(input));
        } catch (e: ParseException) {
            if (s.length == 1) {
                val month = Integer.parseInt(input);
                if (month > 1) {
                    edtExpiryDate.setText("0" + edtExpiryDate.text.toString() + "/");
                    edtExpiryDate.setSelection(edtExpiryDate.text.toString().length);
                }
            } else if (s.length == 2) {
                val month = Integer.parseInt(input);
                if (month > 12) {
                    edtExpiryDate.setText("");
//                        Toast.makeText(
//                            getApplicationContext(),
//                            "Enter a valid month",
//                            Toast.LENGTH_LONG
//                        ).show();
                }
                if (month <= 12) {
                    if (!lastInput.endsWith("/")) {
                        edtExpiryDate.setText(edtExpiryDate.text.toString() + "/");
                        edtExpiryDate.setSelection(edtExpiryDate.text.toString().length);
                    }
                    if (lastInput.endsWith("/")) {
                        edtExpiryDate.setText(
                            edtExpiryDate.text.toString().substring(0, 1)
                        );
                        edtExpiryDate.setSelection(edtExpiryDate.text.toString().length);

                    }
                }
                lastInput = edtExpiryDate.text.toString();
                return

            }
        }
    }

}