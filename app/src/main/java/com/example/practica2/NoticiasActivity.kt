package com.example.practica2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoticiasActivity : AppCompatActivity() {

    private val descripcion = "This 80-foot-tall waterfall on the coast of Big Sur in central " +
            "California that flows year-round from McWay Creek in Julia Pfeiffer Burns State " +
            "Park, about 37 miles south of Carmel."

    private val enlace = "https://developer.android.com/training/sharing/"

    // AQUÍ SE CAMBIAN LAS IMÁGENES: Reemplaza "noticia1" y "noticia2" por el nombre de tus archivos en res/drawable
    private val noticias by lazy {
        listOf(
            Noticia("Apple Vision Pro", descripcion, R.drawable.noticia1, enlace),
            Noticia("Meta Glasses", descripcion, R.drawable.noticia2, enlace)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        val ivPerfil = findViewById<ImageView>(R.id.ivPerfil)
        val rvNoticias = findViewById<RecyclerView>(R.id.rvNoticias)
        val fabCompartir = findViewById<FloatingActionButton>(R.id.fabCompartir)

        val layoutManager = LinearLayoutManager(this)
        rvNoticias.layoutManager = layoutManager
        rvNoticias.adapter = NoticiaAdapter(noticias)

        // Intent explícito hacia PerfilActivity
        ivPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        // Intent implícito: compartir la noticia que se ve primero en pantalla
        fabCompartir.setOnClickListener {
            val pos = layoutManager.findFirstVisibleItemPosition().coerceAtLeast(0)
            val noticia = noticias[pos]
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, noticia.titulo)
                putExtra(Intent.EXTRA_TEXT, noticia.enlace)
            }
            startActivity(Intent.createChooser(sendIntent, "Share"))
        }
    }
}