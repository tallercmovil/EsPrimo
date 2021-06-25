package com.tallercmovil.esprimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.tallercmovil.esprimo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etNumero.typeface = ResourcesCompat.getFont(this, R.font.bryndan_write)

    }

    fun clickBoton(view: View) {
        if(binding.etNumero.text.toString() != ""){
            val numero = Integer.parseInt(binding.etNumero.text.toString())

            if(esPrimo(numero)){
                binding.tvResultado.text = "El número $numero sí es primo"
            }else{
                binding.tvResultado.text = "El número $numero no es primo"
            }

        }else{
            Toast.makeText(this, "Por favor ingresa un valor", Toast.LENGTH_SHORT).show()
            binding.etNumero.error = "Se requiere un valor"
        }
    }

    fun esPrimo(numero: Int): Boolean{
        if(numero <= 1) return false
        else{
            for(i in 2 until numero){
                if(numero%i == 0) return false
            }
        }

        return true
    }
}