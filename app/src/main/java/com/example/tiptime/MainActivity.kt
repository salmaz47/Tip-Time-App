package com.example.tiptime

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiptime.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    var total = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        total = savedInstanceState?.getDouble("total") ?: 0.0
        binding.result.text = total.toString()
        binding.calcBtn.setOnClickListener {
            val cost = binding.et.text.toString().toDouble()
            val tip = when (binding.rGroup.checkedRadioButtonId) {
                R.id.amazing_rBtn -> 0.2
                R.id.good_rBtn -> 0.18
                else -> 0.15
            }
            total = cost * tip
            if (binding.roundTipSwitch.isChecked) {
                total = floor(total)
            }
            binding.result.text = "$$total"

            Snackbar
                .make(binding.root,"Reset Everything",BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction("Proceed"){
                    binding.et.text?.clear()
                    binding.rGroup.check(R.id.amazing_rBtn)
                    binding.roundTipSwitch.isChecked = true
                    binding.result.setText("Tip Amount")
                }
                .setBackgroundTint(getColor(android.R.color.holo_purple))
                .show()

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("total", total)
    }
}