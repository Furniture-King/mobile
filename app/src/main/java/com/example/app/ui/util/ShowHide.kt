package com.example.app.ui.util

import android.view.View
import android.widget.ImageView

/**
 * Manage the switch image on the view
 *
 * @param imgViews a  list of image view
 */
fun showHide(imgViews: Array<ImageView>) {
    for (view in imgViews)
        view.visibility =
            if (view.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
}