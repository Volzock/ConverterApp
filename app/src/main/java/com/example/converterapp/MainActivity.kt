package com.example.converterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    // testing on pixel2, pixel3a, 2.7smartphone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            try {
                et_to.text = (et_from.text.toString().toDouble()*(if (ch.selectedItem != "RUB") 74.0 else 1.0/74)).toString()+" ${if (ch.selectedItem == "RUB") "USD" else "RUB"}"
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Number is wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }

}