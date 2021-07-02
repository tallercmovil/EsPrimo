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

        with(binding) {
            if (etNumero.text.toString() != "") {
                val numero = Integer.parseInt(etNumero.text.toString())

                if (esPrimo(numero)) {
                    tvResultado.text = resources.getString(R.string.si_primo, numero, "!")
                } else {
                    tvResultado.text = getString(R.string.no_primo, numero)
                }

            } else {
                Toast.makeText(this@MainActivity, getString(R.string.ingresa_valor), Toast.LENGTH_SHORT).show()
                etNumero.error = getString(R.string.valor_requerido)
            }
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