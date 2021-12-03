package com.pmdm.recyclerview

/**
 * Programación de aplicaciones multimedia y dispositivos móviles
 * @author Antonio José Sánchez
 * IES. Campanillas (Málaga)
 * curso 2021/22
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.pmdm.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        val datos = mutableListOf(
            Serie("Fundación", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iXU619XXLivE6Mj3dKxlfSQJebq.jpg"),
            Serie( "Lucifer", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wQh2ytX0f8IfC3b2mKpDGOpGTXS.jpg"),
            Serie("Los simpsons", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u8BMLmwoc7YPHKSWawOOqC1c8lJ.jpg"),
            Serie("Locke & Key", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/OPL8TSMru7vqbxk8TwfBWCxByz.jpg"),
            Serie("Downton Abbey", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3mYwiv6rsnEHnbark7a24Jbx2M9.jpg"),
            Serie("CSI", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rUrZj1su654gz9neAiJs9z1y56Z.jpg"),
            Serie("The Witcher", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gh0fdOc2OleDCBjBR1dsQGK490I.jpg"),
            Serie("Chucky", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iF8ai2QLNiHV4anwY1TuSGZXqfN.jpg"),
            Serie("Ojo de halcón", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3ZcJetFxd8fwJ1I5xsLHCMTAKQo.jpg")
        )

        /** Definimos la lambda que gestionará la pulsación corta sobre un elemento del RecyclerView
          * @param it
          */
        val gestionarPulsacionCorta: (Serie) -> Unit = {
            Snackbar.make(binding.root, "Has pulsado sobre la serie ${it.titulo}", Snackbar.LENGTH_LONG)
                .show()
        }

        /** Definimos la lambda que gestionará la pulsación larga sobre un elemento del RecyclerView
         * @param item
         * @param serie
         * @return
         */
        val gestionarPulsacionLarga: (MenuItem, Serie) -> Boolean = { item, serie ->

            when(item.itemId) {
                R.id.serieMenuEditar -> {
                    Snackbar.make(binding.root, "Has pulsado sobre la opción EDITAR sobre la serie ${serie.titulo}", Snackbar.LENGTH_LONG)
                            .show()
                    true
                }

                R.id.serieMenuBorrar -> {
                    Snackbar.make(binding.root, "Has pulsado sobre la opción BORRAR sobre la serie ${serie.titulo}", Snackbar.LENGTH_LONG)
                            .show()
                    true
                }
            }

            false
        }

        // Creamos el adaptador y le pasamos la colección de datos y las lambdas anteriores
        val adaptador = SerieAdaptador(datos, gestionarPulsacionCorta, gestionarPulsacionLarga)

        // Asociamos el adaptador al RecyclerView y establecemos que el tamaño de este
        // componente no variará para así mejorar su rendimiento.
        binding.rv.adapter = adaptador
        binding.rv.setHasFixedSize(true)
    }
}