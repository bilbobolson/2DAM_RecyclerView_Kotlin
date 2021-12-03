package com.pmdm.recyclerview

/**
 * Programación de aplicaciones multimedia y dispositivos móviles
 * @author Antonio José Sánchez
 * IES. Campanillas (Málaga)
 * curso 2021/22
 */

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pmdm.recyclerview.databinding.ListaSeriesBinding

class SerieAdaptador(var datos: MutableList<Serie>,
                     val gestionarPulsacionCorta: (Serie) -> Unit,
                     val gestionarPulsacionLarga: (MenuItem, Serie) -> Boolean): RecyclerView.Adapter<SerieAdaptador.SerieContenedor>()
{
    /**
     * Devuelve el tamaño de la colección de datos
     */
    override fun getItemCount(): Int = datos.size

    /**
     * Se llama cada vez que se crea un contenedor para un ítem de la colección de datos
     * @param parent
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieContenedor
    {
        val inflador = LayoutInflater.from(parent.context)
        val binding = ListaSeriesBinding.inflate(inflador, parent, false)

        return SerieContenedor(binding)
    }

    /**
     * Se llama, tras crear el contenedor, para vincular el ítem correspondiente de la
     * colección de datos con dicho contenedor.
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: SerieContenedor, position: Int)
    {
        holder.bindSerie(datos[position])
    }

    /**
     * Definimos la clase CONTENEDORA dentro del propio adaptador. A diferencia de lo que
     * haríamos con Java, en Kotlin debemos marcar la clase SERIECONTENEDOR como interna
     * para poder acceder a los miembros de la clase SERIEADAPTADOR.
     */
    inner class SerieContenedor(val binding: ListaSeriesBinding): RecyclerView.ViewHolder(binding.root)
    {
        /**
         * Completamos las vistas con los datos de una serie en particular que se
         * nos pasa como parámetro.
         * @param serie
         */
        fun bindSerie(serie: Serie)
        {
            // mostramos el título de la serie
            binding.serieTitulo.text = serie.titulo

            // cargar el poster dentro de la vista correspondiente
            Glide.with(binding.root)
                 .load(serie.poster)
                 .into(binding.seriePoster)

            // definimos un listener para pulsación simple
            binding.root.setOnClickListener { gestionarPulsacionCorta(serie) }

            // definimos un listener para pulsación larga
            binding.root.setOnLongClickListener {

                val pop = PopupMenu(binding.root.context, binding.serieTitulo)
                    pop.inflate(R.menu.lista_series_context_menu)
                    pop.setOnMenuItemClickListener {
                        gestionarPulsacionLarga(it, serie)
                    }

                    pop.show()

                true
            }
        }
    }

}