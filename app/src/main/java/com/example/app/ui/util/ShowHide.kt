package com.example.app.ui.util

import android.view.View
import android.widget.ImageView

// Manage the toggle event on heart's article click
fun showHide(imgViews: Array<ImageView>) {
    for (view in imgViews)
        view.visibility =
            if (view.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
}