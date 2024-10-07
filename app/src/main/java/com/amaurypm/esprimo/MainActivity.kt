package com.amaurypm.esprimo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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


        binding.etNumero.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnVerificar.isEnabled = validateFields()
            }

        })

        binding.btnVerificar.setOnClickListener {
            //click al botón

            if (binding.etNumero.text.toString().isNotEmpty()) {

                val numero = binding.etNumero.text.toString().toInt()

                val character = "!"

                if (esPrimo(numero)) {
                    binding.tvResultado.text = getString(R.string.si_primo, numero, character)
                } else {
                    binding.tvResultado.text = getString(R.string.no_primo, numero)
                }
            }else{
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

    private fun esPrimo(num: Int): Boolean {
        if (num <= 1) return false

        for (i in 2 until num) {
            if (num % i == 0) return false
        }

        return true
    }

    private fun validateFields(): Boolean{
        return binding.etNumero.text.toString().isNotEmpty()
    }


}