package com.unir.calculadorasalarioneto

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Recogemos el textView

        val resultTV = findViewById<TextView>(R.id.resultTV)

        //Recoger los putExtra

        val SBM: String = intent.extras?.getString("SBM").orEmpty()
        val tipo : String = intent.extras?.getString("tipo").orEmpty()
        val salarioNeto: Double = intent.getDoubleExtra("salarioNeto", 0.0)
        val salarioNetoMensual: Double = intent.getDoubleExtra("salarioNetoMensual", 0.0)
        val SBA: Double = intent.getDoubleExtra("SBA", 0.0)
        val deducciones = intent.getDoubleExtra("deducciones" ,0.0)

        //Modificamos el contenido del resultTV

        resultTV.text ="Salario Bruto Anual: $SBA\n Salario Bruto Mensual: $SBM\nTipo de Retención: $tipo\nSalario Neto Anual: $salarioNeto\n" +
                "Salario Neto Mensual: $salarioNetoMensual\n Deducciones: $deducciones"


    }
}