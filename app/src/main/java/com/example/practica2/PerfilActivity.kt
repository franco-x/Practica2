package com.example.practica2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Color de la barra de estado
        window.statusBarColor = ContextCompat.getColor(this, R.color.gris_barra)

        // Configuración de la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                title = "Perfil"
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            }
        }

        // Listeners para los iconos y filas de la pantalla
        findViewById<View>(R.id.ivBuscar)?.setOnClickListener {
            Toast.makeText(this, "Buscar", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.ivCorreo)?.setOnClickListener {
            Toast.makeText(this, "Correo", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.rowInformacion)?.setOnClickListener {
            Toast.makeText(this, "Información", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.rowNotificaciones)?.setOnClickListener {
            Toast.makeText(this, "Notificaciones", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.rowFavoritos)?.setOnClickListener {
            Toast.makeText(this, "Favoritos", Toast.LENGTH_SHORT).show()
        }

        // Cerrar sesión
        findViewById<MaterialButton>(R.id.btnCerrarSesion)?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_perfil, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Regresa a NoticiasActivity
                true
            }
            R.id.action_privacidad -> {
                startActivity(Intent(this, PrivacidadActivity::class.java))
                true
            }
            R.id.action_contactos -> {
                startActivity(Intent(this, ContactosActivity::class.java))
                true
            }
            R.id.action_central -> {
                Toast.makeText(this, "Central", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}