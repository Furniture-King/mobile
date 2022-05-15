package com.example.app.ui.OtherFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import app.R


/**
 * A simple [Fragment] subclass.
 * Use the [ButtonBack.newInstance] factory method to
 * create an instance of this fragment.
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