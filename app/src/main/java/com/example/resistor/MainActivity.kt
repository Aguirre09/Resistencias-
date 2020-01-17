package com.example.resistor


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.pow
class MainActivity : AppCompatActivity() {


    //Spinners
    lateinit var SpnC1 : Spinner
    lateinit var SpnC2 : Spinner
    lateinit var SpnC3 : Spinner
    lateinit var SpnC4 : Spinner
    //Boton
    lateinit var cal : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SpnC1 = findViewById<View>(R.id.SpnColor1) as Spinner
        SpnC2 = findViewById<View>(R.id.SpnColor2) as Spinner
        SpnC3 = findViewById<View>(R.id.SpnColor3) as Spinner
        SpnC4 = findViewById<View>(R.id.SpnColor4) as Spinner
        // buton
        cal = findViewById<View>(R.id.BtnCalculateIt) as Button


        // Adaptar  Spinner
        val adapter1 = ArrayAdapter.createFromResource(this, R.array.Colores1,android.R.layout.simple_spinner_item)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SpnC1.adapter=adapter1
        SpnC2.adapter=adapter1
        SpnC3.adapter=adapter1

            // Diferentes colores
        val adapter2 = ArrayAdapter.createFromResource(this, R.array.Colores2,android.R.layout.simple_spinner_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        SpnC4.adapter=adapter2

        // Al momento de hacer click en CALCULAR


        // mostrara  el resultado de la operacion
        cal.setOnClickListener {
            Resultad.text = Numero_Res(SpnC1.selectedItemPosition, SpnC2.selectedItemPosition,SpnC3.selectedItemPosition) + "" + " y Tiene valor de : " + Tolerancia(SpnC4.selectedItemPosition)


        }

    }


    // Cada Spinner, genera una posicion en formato int, por lo que se procedió a trabajar con estos valores para el calculo

    // Se procede a crear funcion para obtener la tolerancia


    private fun Tolerancia(Value: Int):String {
        if (Value==0) {
            return "+/- 1% de Tolerancia"
        }
        if (Value== 1) {
           return  "+/- 2% de Tolerancia "
       }
        if (Value == 2 ) {
            return "+/- 5% de Tolerancia"
        }else {
            return  "+/- 10% de Tolerancia"

        }


    }


    //Funcion para generar el valor de la resistencia en pantalla
    //Obtengo el valor de la posicion segun el color
    //
    // Debido a que la posicion de los Spinner esta en Int : 0,1,2,3...
   // Para las resistencias de 4 bandas :
    // La 1ra y 2da banda definen el valor de la resistencia, la tercera banda defina el multiplicador
    // multiplicador de 10 elevado al valor de la tercera banda

    private fun Numero_Res(Value1: Int, Value2: Int, Value3: Int): String {
        val obtener: String


        obtener = Value1.toString() + Value2.toString()

        // se pasa la cadena de caracter a numerico y luego se multiplica a la potencia de 10 segun la tabla de Resistencias
        val Resultado = obtener.toInt() * pow(10.0, Value3.toDouble())



        // Condicional para comprobar el valor de Unidades de Resistencia
        //////////////////////////////////////////////////////
        //Para regresar el valor en KΩ
        if (Resultado / 1000 >= 1 && Resultado / 1000 < 1000) {
            return (Resultado / 1000).toString() + ""+"KΩ"
        }
        //Para regresar el valor en MΩ
        return if (Resultado / 1000000 >= 1) {
            (Resultado / 1000000).toString() + ""+"MΩ"
        } else {
            //Regresamos el valor en Ω
            Resultado.toString() + ""+"Ω"
        }

    }
}

















