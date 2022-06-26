package com.example.app.ui.OtherFragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import app.R


/**
 * Fragment ButtonBack
 *
 * Allow to get back on the previous activity
 */
class ButtonBack : Fragment(R.layout.fragment_button_back) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgBack = view.findViewById(R.id.imgBack) as ImageView
        imgBack.setOnClickListener{
            activity?.finish()
        }
    }
}