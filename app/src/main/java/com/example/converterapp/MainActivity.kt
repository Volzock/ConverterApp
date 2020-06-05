package com.example.converterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    // testing on pixel2, pixel3a
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener {
            try {
                et_to.text = (et_from.text.toString().toDouble()*74).toString()+"rub"
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Number is wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }

}