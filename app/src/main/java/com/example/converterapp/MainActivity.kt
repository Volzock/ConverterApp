package com.example.converterapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    // testing on pixel2, pixel3a, 2.7smartphone
    val cur = mutableMapOf<String, String>("RUB" to "1.0", "EUR" to "77.8", "USD" to "68.7", "GBP" to "86.3", "UAH" to "2.6", "JPY" to "0.6", "CNY" to "9.6")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            try {
                // et_to.text = (et_from.text.toString().toDouble()*(cur[from_cur.selectedItem]?.toDouble() ?: 1)*).toString()+" ${to_cur.selectedItem}"
                et_to.text = (et_from.text.toString().toDouble() * (cur[from_cur.selectedItem]?.toDouble() ?: 1.0) / (cur[to_cur.selectedItem]?.toDouble() ?: 1.0)).toString() + " ${to_cur.selectedItem}"
            } catch(e: NumberFormatException) {
                Toast.makeText(applicationContext, "Number is wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        }

    }

}