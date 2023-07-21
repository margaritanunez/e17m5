package com.example.e17m5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.e17m5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var divisas = listOf<String> ("Dólar", "Pesos", "Euro")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        binding.spinner2.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        initListener()
    }

    private fun initListener() {
        binding.convertirButton.setOnClickListener {
           val monto = binding.entradatxt.text.toString().toDouble()
           val divisaActual = binding.spinner1.selectedItem.toString()
           val divisaCambio = binding.spinner2.selectedItem.toString()
           val resultado = conversorDivisas(monto, divisaActual, divisaCambio)
           binding.resultadotv.text = resultado.toString()
        }
        binding.resetearbutton.setOnClickListener{
            limpiar()
        }
    }
    fun conversorDivisas (monto:Double, divisaActual: String, divisaCambio : String): Double {
        var resultado = monto

        when (divisaActual) {
            "Dólar" -> if (divisaCambio == "Pesos"){
                resultado = monto * 817.0
            } else if (divisaCambio == "Dólar" ) {
                resultado = monto * 1.0
            } else if (divisaCambio == "Euro"){
                resultado = monto * 0.89
            }

            "Pesos" -> if (divisaCambio=="Pesos") {
                resultado = monto * 1.0
            } else if (divisaCambio == "Dólar") {
                resultado = monto * 0.001
            } else if (divisaCambio == "Euro") {
                resultado = monto * 0.001
            }

            "Euro" -> if (divisaCambio == "Pesos") {
                resultado = monto * 910.0
            } else if (divisaCambio == "Dólar") {
                resultado = monto * 1.11
            } else if (divisaCambio == "Euro"){
                resultado = monto * 1.0
            }
            else {
                resultado = monto
            }
        }
        return resultado
    }
    fun limpiar() {
        binding.resultadotv.text = ""
        binding.entradatxt.setText("")
    }

}