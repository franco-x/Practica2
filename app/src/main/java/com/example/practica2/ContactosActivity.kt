package com.example.practica2

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class ContactosActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    private val contactos = listOf(
        "Prensa Editorial: +51 1 987987987",
        "Prensa Editorial: +51 1 987987987",
        "Prensa Editorial: +51 1 987987987",
        "Prensa Editorial: +51 1 987987987",
        "Prensa Editorial: +51 1 987987987"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactos)

        // Color de barra de estado
        window.statusBarColor = ContextCompat.getColor(this, R.color.gris_barra)

        // Configuración de Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                title = "Contactos"
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            }
        }

        // Configuración de ListView con el ID del TextView (R.id.tvContacto)
        listView = findViewById(R.id.lvContactos)
        listView.adapter = ArrayAdapter(
            this,
            R.layout.item_contacto,
            R.id.tvContacto, // <--- CORRECCIÓN CLAVE
            contactos
        )

        // Registrar Menú Contextual para toque prolongado
        registerForContextMenu(listView)
    }

    // ---- Menú de opciones de la Toolbar (3 puntos) ----
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_general, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_ayuda -> {
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_acerca -> {
                Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ---- Menú Contextual (Toque prolongado) ----
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v.id == R.id.lvContactos) {
            val info = menuInfo as? AdapterView.AdapterContextMenuInfo
            if (info != null) {
                menu.setHeaderTitle(contactos[info.position].substringBefore(":"))
                menuInflater.inflate(R.menu.menu_contextual_contacto, menu)
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as? AdapterView.AdapterContextMenuInfo ?: return super.onContextItemSelected(item)
        val contacto = contactos[info.position]
        val nombre = contacto.substringBefore(":")
        val numero = contacto.substringAfter(":").trim()

        return when (item.itemId) {
            R.id.ctx_llamar -> {
                Toast.makeText(this, "Llamando a $nombre ($numero)...", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.ctx_mensaje -> {
                Toast.makeText(this, "Enviando mensaje a $nombre ($numero)...", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}