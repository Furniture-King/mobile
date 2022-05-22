package com.example.app.ui.OtherFragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.fragment.app.Fragment
import app.R
import java.util.Collections.rotate


/**
 * A simple [Fragment] subclass.
 * Use the [ButtonBack.newInstance] factory method to
 * create an instance of this fragment.
 */
class ButtonDropDown : Fragment(R.layout.fragment_button_drop_down) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgBack = view.findViewById(R.id.imgDropDown) as ImageView
        imgBack.setOnClickListener {
            val refinerProduct =
                activity?.findViewById(R.id.refinerProduct) as View
            val imgDropDown = activity?.findViewById(R.id.imgDropDown) as View


            imgBack.rotation = if (imgBack.rotation == 90F) 270F else 90F
            refinerProduct.visibility =
                if (imgDropDown.rotation == 90F) View.GONE else View.VISIBLE
        }
    }
}