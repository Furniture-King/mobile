package com.example.app.ui.OtherFragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import app.R


/**
 * Fragment ButtonDropDown
 *
 * Allow to show the filter in the search product result
 */
class ButtonDropDown : Fragment(R.layout.fragment_button_drop_down) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgDropDown = view.findViewById(R.id.imgDropDown) as ImageView
        imgDropDown.setOnClickListener {
            val refinerProduct =
                activity?.findViewById(R.id.refinerProduct) as View

            imgDropDown.rotation = if (imgDropDown.rotation == 270F) 90F else 270F
            refinerProduct.visibility =
                if (imgDropDown.rotation == 90F) View.GONE else View.VISIBLE
        }
    }
}