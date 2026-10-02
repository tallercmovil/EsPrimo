package com.amaurypm.esprimo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amaurypm.esprimo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnVerificar.setOnClickListener {
            if(validateFields()){
                val numero = binding.etNumero.text.toString().toInt()
                if (esPrimo(numero)) {
                    binding.tvResultado.text = "El número $numero sí es primo"
                } else {
                    binding.tvResultado.text = "El número $numero no es primo"
                }
            }
        }
    }

    fun esPrimo(numero: Int): Boolean{
        if(numero < 2) return false
        for(i in 2 until numero){
            if(numero%i == 0) return false
        }
        return true
    }

    fun validateFields() = binding.etNumero.text.toString().isNotEmpty()

}