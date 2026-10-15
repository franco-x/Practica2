package com.example.practica2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreo = findViewById<TextInputEditText>(R.id.etCorreo)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val tilCorreo = findViewById<TextInputLayout>(R.id.tilCorreo)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val btnIngresar = findViewById<MaterialButton>(R.id.btnIngresar)
        val btnRegistrar = findViewById<MaterialButton>(R.id.btnRegistrar)

        btnIngresar.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val password = etPassword.text.toString().trim()

            tilCorreo.error = null
            tilPassword.error = null

            var valido = true
            if (correo.isEmpty()) {
                tilCorreo.error = "Ingresa tu correo"
                valido = false
            }
            if (password.isEmpty()) {
                tilPassword.error = "Ingresa tu contraseña"
                valido = false
            }

            if (valido) {
                // Intent explícito hacia NoticiasActivity (login simulado)
                startActivity(Intent(this, NoticiasActivity::class.java))
            }
        }

        btnRegistrar.setOnClickListener {
            Toast.makeText(this, "Registro no disponible en esta versión", Toast.LENGTH_SHORT).show()
        }
    }
}
