package com.example.app.ui.pages.settings

import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.TOP
import androidx.core.view.isEmpty
import app.R
import app.databinding.ActivityPaymentBinding

/**
 * Activity MeansOfPayment
 *
 * Show all the means of payment that the user had
 */
class MeansOfPaymentActivity : AppCompatActivity() {
    // Link this activity to the view xml
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerViewMeansOfPayment.apply {
            if (isEmpty()) {
                setVisibility(View.GONE);
                binding.tvEmptyView.setVisibility(View.VISIBLE);
                val constraintSet = ConstraintSet()
                constraintSet.connect(
                    binding.swtAddMeansOfPayment.id, // text view to change constraint
                    TOP, // put text view top side bottom of button
                    binding.tvEmptyView.id, // button to put text view bellow it
                    BOTTOM, // button bottom to put text view bellow it
                    16 // margin (optional)
                )

            } else {
                setVisibility(View.VISIBLE);
                binding.tvEmptyView.setVisibility(View.GONE);
            }
        }
        binding.ftMeansOfPayment.visibility = View.GONE

        val swtAddMeansOfPayment = findViewById<Switch>(R.id.swtAddMeansOfPayment)
        swtAddMeansOfPayment?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                binding.ftMeansOfPayment.visibility = View.VISIBLE
            else binding.ftMeansOfPayment.visibility = View.GONE

        }
    }
}
