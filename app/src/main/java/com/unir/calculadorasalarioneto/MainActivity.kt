package com.unir.calculadorasalarioneto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recoger todas mis variables en el botón calcular

        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.calcularButton).setOnClickListener {

            val SBA = findViewById<EditText>(R.id.ISBA).text.toString().toDouble()
            val NP = findViewById<EditText>(R.id.INP).text.toString().toDouble()
            val E = findViewById<EditText>(R.id.IE)
            val GP = findViewById<EditText>(R.id.IGP)
            val GD = findViewById<EditText>(R.id.IGD).text.toString().toDouble()
            val EC = findViewById<EditText>(R.id.IEC)
            val NH = findViewById<EditText>(R.id.INH).text.toString().toDouble()



            //Salario bruto mensual




            var tipo : String = ""
            var retencion: Double = 0.0
            var SBM : Double = 0.0
            var salarioNeto : Double= 0.0
            var salarioNetoMensual: Double= 0.0
            var deducciones = 0.0

                //Salario Neto y Retenciones
                if (SBA != null) {
                     SBM = SBA / NP




                    // Salario Neto y Retenciones
                    when {
                        SBA <= 12450 -> {
                            tipo = "19%"
                            retencion = SBA * 0.19
                        }
                        SBA > 12450 && SBA <= 20199 -> {
                            tipo = "24%"
                            retencion = SBA * 0.24
                        }
                        SBA > 20200 && SBA <= 35199 -> {
                            tipo = "30%"
                            retencion = SBA * 0.30
                        }
                        SBA > 35200 && SBA <= 59999 -> {
                            tipo = "37%"
                            retencion = SBA * 0.37
                        }
                        SBA > 60000 && SBA <= 299999 -> {
                            tipo = "45%"
                            retencion = SBA * 0.45
                        }
                        SBA > 300000 -> {
                            tipo = "47%"
                            retencion = SBA * 0.47
                        }



                }

                     salarioNeto = SBA - retencion
            }

            salarioNetoMensual = salarioNeto/12

            if(NH != null){
                deducciones = NH * 150
            }

            if(GD != null && GD > 33){

                deducciones += (SBA*0.05)
            }


          //Redondear a dos decimales

            SBM = String.format("%.2f", SBM).toDouble()
            salarioNeto = String.format("%.2f", salarioNeto).toDouble()
            salarioNetoMensual = String.format("%.2f", salarioNetoMensual).toDouble()
            deducciones = String.format("%.2f", deducciones).toDouble()






            //Navegar a la siguiente View

            val intent = Intent(this, ResultLoginActivity::class.java)

            //Valores que queremos pasar a la otra View

            intent.putExtra("SBM", SBM.toString())
            intent.putExtra("tipo", tipo)
            intent.putExtra("salarioNeto", salarioNeto)
            intent.putExtra("salarioNetoMensual", salarioNetoMensual)
            intent.putExtra("SBA", SBA)
            intent.putExtra("deducciones", deducciones)

            //Iniciamos la otra activity

            startActivity(intent)



        }
    }
    }

