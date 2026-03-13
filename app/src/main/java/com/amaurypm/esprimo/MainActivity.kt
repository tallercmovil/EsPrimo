package com.amaurypm.esprimo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
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

        /*binding.etNumber.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {23
                if(binding.etNumber.text.isNotEmpty()) binding.btnCheck.isEnabled = true
                else binding.btnCheck.isEnabled = false
            }
        })*/

        binding.etNumber.addTextChangedListener {
            if(binding.etNumber.text.isNotEmpty()) binding.btnCheck.isEnabled = true
            else binding.btnCheck.isEnabled = false
        }

        binding.btnCheck.setOnClickListener {

            if(binding.etNumber.text.isNotEmpty()){
                val number = binding.etNumber.text.toString().toInt()

                //Verificamos si el número es primo o no
                if(esPrimo(number)){
                    binding.tvResult.text = "El número $number sí es primo"
                }else{
                    binding.tvResult.text = "El número $number no es primo"
                }
            }else{
                Toast.makeText(
                    this,
                    "Por favor ingresa un número",
                    Toast.LENGTH_SHORT)
                    .show()
                binding.etNumber.error = "Se requiere un número"
                binding.etNumber.requestFocus()
            }


        }
    }

    /*fun esPrimo(number: Int): Boolean{
        if(number < 2) return false
        for(i in 2 until number){
            if(number%i ==0) return false
        }
        return true
    }*/

    fun esPrimo(numero: Int): Boolean {
        if (numero < 2) return false
        if (numero == 2 || numero == 3) return true
        if (numero % 2 == 0 || numero % 3 == 0) return false

        var i = 5
        while (i * i <= numero) {
            if (numero % i == 0 || numero % (i + 2) == 0) return false
            i += 6
        }
        return true
    }
}