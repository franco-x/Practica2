package com.example.practica2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoticiaAdapter(
    private val noticias: List<Noticia>
) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    class NoticiaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.ivNoticia)
        val titulo: TextView = view.findViewById(R.id.tvTituloNoticia)
        val descripcion: TextView = view.findViewById(R.id.tvDescripcionNoticia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.imagen.setImageResource(noticia.imagen) // o noticia.imagen según tu modelo
        holder.titulo.text = noticia.titulo
        holder.descripcion.text = noticia.descripcion
    }

    override fun getItemCount(): Int = noticias.size
}