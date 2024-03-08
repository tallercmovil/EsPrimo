package com.amaurypm.esprimo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amaurypm.esprimo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnVerificar.setOnClickListener {
            if (binding.etNumero.text.isNotEmpty()) {

                val numero = binding.etNumero.text.toString().toInt()
                if(esPrimo(numero)){
                    binding.tvResultado.text = "El número $numero sí es primo"
                }else{
                    binding.tvResultado.text = "El número $numero no es primo"
                }


                //Verificar si el número es primo o no
            }else{
                Toast.makeText(this, "Por favor ingresa un número", Toast.LENGTH_SHORT).show()
                binding.etNumero.error = "Es requerido"
                binding.etNumero.requestFocus()
            }
        }
    }

    fun esPrimo(numero: Int): Boolean{
        if(numero<=1) return false

        for(i in 2 until numero){
            if(numero % i == 0) return false
        }

        return true
    }
}
