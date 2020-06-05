package com.example.converterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    val cur = arrayOf(
        "CAD", "HKD", "ISK", "PHP", "DKK", "HUF", "CZK", "GBP", "RON", "SEK", "IDR", "INR", "BRL", "RUB", "HRK", "JPY",
        "THB", "CHF", "EUR", "MYR", "BGN", "TRY", "CNY", "NOK", "NZD", "ZAR", "USD", "MXN", "SGD", "AUD", "ILS", "KRW", "PLN"
    )
    val URL = "https://api.exchangeratesapi.io/latest?base="

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        val spinAdapt = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cur)
        to_cur.adapter = spinAdapt
        from_cur.adapter = spinAdapt

        btn.setOnClickListener {
            loadServerData()
        }
    }

    private fun loadServerData() {
        val client: OkHttpClient = OkHttpClient()
        var currency: Double = 0.0
        val request: Request = Request.Builder().url("${URL}${to_cur.selectedItem}").build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(applicationContext, "Server connection error, please reconnect to the Internet", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                try {
                    val gson = GsonBuilder().create()
                    val cur = gson.fromJson(response.body?.string(), DataCur::class.java)
                    when (from_cur.selectedItem) {
                        "CAD" -> currency = cur.rates.CAD
                        "HKD" -> currency = cur.rates.HKD
                        "ISK" -> currency = cur.rates.ISK
                        "PHP" -> currency = cur.rates.PHP
                        "DKK" -> currency = cur.rates.DKK
                        "HUF" -> currency = cur.rates.HUF
                        "CZK" -> currency = cur.rates.CZK
                        "GBP" -> currency = cur.rates.GBP
                        "RON" -> currency = cur.rates.RON
                        "SEK" -> currency = cur.rates.SEK
                        "IDR" -> currency = cur.rates.IDR
                        "INR" -> currency = cur.rates.INR
                        "BRL" -> currency = cur.rates.BRL
                        "RUB" -> currency = cur.rates.RUB
                        "HRK" -> currency = cur.rates.HRK
                        "JPY" -> currency = cur.rates.JPY
                        "THB" -> currency = cur.rates.THB
                        "CHF" -> currency = cur.rates.CHF
                        "EUR" -> currency = cur.rates.EUR
                        "MYR" -> currency = cur.rates.MYR
                        "BGN" -> currency = cur.rates.BGN
                        "TRY" -> currency = cur.rates.TRY
                        "CNY" -> currency = cur.rates.CNY
                        "NOK" -> currency = cur.rates.NOK
                        "NZD" -> currency = cur.rates.NZD
                        "ZAR" -> currency = cur.rates.ZAR
                        "USD" -> currency = cur.rates.USD
                        "MXN" -> currency = cur.rates.MXN
                        "SGD" -> currency = cur.rates.SGD
                        "AUD" -> currency = cur.rates.AUD
                        "ILS" -> currency = cur.rates.ILS
                        "KRW" -> currency = cur.rates.KRW
                        "PLN" -> currency = cur.rates.PLN
                    }

                    try {
                        if (et_from.text.toString() == "") {
                            runOnUiThread{Toast.makeText(applicationContext, "Number is wrong, please try again", Toast.LENGTH_SHORT).show()}
                            return
                        }
                        runOnUiThread{et_to.text = "${(et_from.text.toString().toDouble() * currency).toString()} ${from_cur.selectedItem}"}
                    } catch(e: NumberFormatException) {
                        runOnUiThread{Toast.makeText(applicationContext, "Number is wrong, please try again", Toast.LENGTH_SHORT).show()}
                        return
                    }

                } catch(e: NullPointerException) {
                    runOnUiThread{Toast.makeText(applicationContext, "Server connection error, please try again", Toast.LENGTH_SHORT).show()}
                    return
                }
            }

        })
    }
}

class DataCur(val rates: Currencies)
class Currencies(val CAD : Double, val HKD : Double, val ISK : Double, val PHP : Double, val DKK : Double,
               val HUF : Double, val CZK : Double, val GBP : Double, val RON : Double, val SEK : Double,
               val IDR : Double, val INR : Double, val BRL : Double, val RUB : Double, val HRK : Double,
               val JPY : Double, val THB : Double, val CHF : Double, val EUR : Double, val MYR : Double,
               val BGN : Double, val TRY : Double, val CNY : Double, val NOK : Double, val NZD : Double,
               val ZAR : Double, val USD : Double, val MXN : Double, val SGD : Double, val AUD : Double,
               val ILS : Double, val KRW : Double, val PLN : Double)
