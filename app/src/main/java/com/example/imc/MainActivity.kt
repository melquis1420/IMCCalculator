package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //hide support bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttomRefresh.setOnClickListener() {
            deleteAll()
        }

        buttomCalculate.setOnClickListener() {
            validate()
        }

    }

    /**
     * Clean all fields.
     */

    private fun deleteAll() {
        editPeso.text = null
        editAltura.text = null
        informeDados.text = "Informe seus dados "
    }

    /**
     * Calculate the IMC.
     */
    private fun calculate() {
        val weight = editPeso.text.toString().toFloat()
        val height = editAltura.text.toString().toFloat() / 100
        val imc: Float = weight / (height * height)
        val format = "%.2f".format(imc)

        informeDados.text = if (imc < 18.6) {
            "Abaixo do Peso\nIMC -> $format"
        } else (if (imc >= 18.6 && imc < 24.9) {
            "Peso Ideal\nIMC -> $format"
        } else if (imc >= 24.9 && imc < 29.9) {
            "Levemente Acima do Peso\nIMC -> $format"
        } else if (imc >= 29.9 && imc < 34.9) {
            "Obesidade Grau 1\nIMC -> $format"
        } else if (imc >= 34.9 && imc < 39.9) {
            "Obesidade Grau 2\nIMC -> $format"
        } else {
            "Obesidade Grau 3\nIMC -> $format"
        })
    }

    /**
     * Check if all fields are filled.
     */
    private fun validate() {
        if (editAltura.text.toString() != "" && editPeso.text.toString() != "") {
            calculate()
        } else if (editPeso.text.toString() == "") {
            Toast.makeText(this, "Insira seu peso", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Insira a sua altura", Toast.LENGTH_SHORT).show()
        }
    }
}