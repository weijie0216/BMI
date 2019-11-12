package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        if(editTextHeight.text.isEmpty()) {
            editTextHeight.setError(getString(R.string.input_error))
            return
        }
        val height: Float = editTextHeight.text.toString().toFloat()

        if(editTextWeight.text.isEmpty()) {
            editTextWeight.setError(getString(R.string.input_error))
            return
        }
        val weight: Float = editTextWeight.text.toString().toFloat()

        val bmi = weight/(height/100).pow(2)

        if(bmi < 18.5) {
            textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.under))
            imageViewProfile.setImageResource(R.drawable.under)
        }
        else if(bmi in 18.5 .. 25.0) {
            textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.normal))
            imageViewProfile.setImageResource(R.drawable.normal)
        }
        else {
            textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.over))
            imageViewProfile.setImageResource(R.drawable.over)
        }

        buttonCalculate.isClickable = false
        buttonCalculate.isEnabled = false
    }

    fun reset(view: View) {
        editTextWeight.text.clear()
        editTextHeight.text.clear()
        textViewBMI.text = String.format("%s", getString(R.string.bmi))
        imageViewProfile.setImageResource(R.drawable.empty)

        buttonCalculate.isClickable = true
        buttonCalculate.isEnabled = true
    }
}
