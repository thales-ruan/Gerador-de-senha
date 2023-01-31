package com.example.geradordesenha

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.geradordesenha.databinding.ActivityMainBinding
import com.google.android.material.R

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val alfabeto = arrayOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
        "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "Z"
    )

    private val caracters = arrayOf("@", "$", "!", "&", "%", "#")

    private val dificuldadeSenha: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adicionarList()
        initSpiner()

        copiar()
    }

    private fun copiar() {
        binding.buttonCopiar.setOnClickListener {
            val textToCopy = binding.tVResult.text.toString()
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Texto copiado", textToCopy)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this@MainActivity, "Texto copiado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun adicionarList() {

        dificuldadeSenha.add(0, "Fácil")
        dificuldadeSenha.add(1, "Médio")
        dificuldadeSenha.add(2, "Difícil")
    }

    private fun initSpiner() {
        val adapterDificuldade: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                dificuldadeSenha
            )
        binding.autoCompleteDificuldade.setAdapter(adapterDificuldade)

        binding.autoCompleteDificuldade.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            Toast.makeText(this@MainActivity, "Senha nova Gerada ", Toast.LENGTH_SHORT)
                .show()

            when (selectedItem) {
                "Fácil" -> {
                    senhaFacil()
                }
                "Médio" -> {
                    senhaMedia()
                }
                "Difícil" -> {
                    senhaDificil()
                }
            }
        }
    }

    private fun senhaFacil() {
        val quatroNumerosAleatorios = (999..9999).random().toString()
        binding.tVResult.text = quatroNumerosAleatorios
        binding.buttonCopiar.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun senhaMedia() {
        val primeiraLetra = alfabeto.indices.random()
        val segundaLetra = alfabeto.indices.random()
        val primeiraLetraAle = alfabeto[primeiraLetra]
        val segundaLetraAle = alfabeto[segundaLetra]

        val quatroNumerosAleatorios = (999..9999).random().toString()

        binding.buttonCopiar.visibility = View.VISIBLE

        binding.tVResult.text = "$primeiraLetraAle$quatroNumerosAleatorios$segundaLetraAle"
    }

    @SuppressLint("SetTextI18n")
    private fun senhaDificil() {
        val doisNumerosAleatorio1 = (10..99).random().toString()
        val doisNumerosAleatorio2 = (10..99).random().toString()
        val primeiraLetra = alfabeto.indices.random()
        val segundaLetra = alfabeto.indices.random()
        val primeiroCaracter = caracters.indices.random()
        val segundoCaracter = caracters.indices.random()
        val primeiraLetraAle = alfabeto[primeiraLetra]
        val segundaLetraAle = alfabeto[segundaLetra]
        val primeiraCaracterAle = caracters[primeiroCaracter]
        val segundoCaracterAle = caracters[segundoCaracter]

        binding.buttonCopiar.visibility = View.VISIBLE

        binding.tVResult.text =
            "$primeiraCaracterAle$primeiraLetraAle$doisNumerosAleatorio1" +
                    "$segundaLetraAle$doisNumerosAleatorio2$segundoCaracterAle"
    }

}