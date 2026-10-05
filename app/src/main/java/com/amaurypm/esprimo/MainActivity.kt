package com.amaurypm.esprimo

import android.os.Bundle
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

        binding.etNumero.addTextChangedListener {
            binding.btnVerificar.isEnabled = binding.etNumero.text.toString().isNotEmpty()
        }

        binding.btnVerificar.setOnClickListener {
            if(validateFields()){
                val numero = binding.etNumero.text.toString().toInt()
                if (esPrimo(numero)) {
                    binding.tvResultado.text = getString(R.string.si_primo, numero, "!")
                } else {
                    binding.tvResultado.text = getString(R.string.no_primo, numero)
                }
            }
            else{
                Toast.makeText(
                    this,
                    getString(R.string.ingresa_valor),
                    Toast.LENGTH_SHORT
                ).show()
                binding.etNumero.error = getString(R.string.valor_requerido)
                binding.etNumero.requestFocus()
            }
        }
    }

    /*fun esPrimo(numero: Int): Boolean{
        if(numero < 2) return false
        for(i in 2 until numero){
            if(numero%i == 0) return false
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

    fun validateFields() = binding.etNumero.text.toString().isNotEmpty()

}