package com.utad.ud6_eltiempo.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.ud6_eltiempo.R
import kotlinx.coroutines.launch


class MainFragment() : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var rvTiempoCincoDias: RecyclerView
    private var adapter: MainAdapter? = null

    // private lateinit var pbLoading: View


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  initList()
        rvTiempoCincoDias = view.findViewById(R.id.rvTiempoDias)
        initList()
        listenEvents(view)
        viewModel.getTiempoActual()
        viewModel.getTiempoCincoDias()


    }


    private fun initList() {
        val mLayoutManager = LinearLayoutManager(context)
        rvTiempoCincoDias.layoutManager = mLayoutManager

        adapter = MainAdapter()
        rvTiempoCincoDias.adapter = adapter
    }


    private fun listenEvents(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tiempoActual.collect {
                        view.findViewById<TextView>(R.id.tbUbicacion).text = it.firstOrNull()?.name

                        view.findViewById<TextView>(R.id.tvTemperaturaHoy).text =
                            it.firstOrNull()?.main?.temp?.toString()

                        view.findViewById<TextView>(R.id.tvHumedad).text = "Humedad: " +
                                it.firstOrNull()?.main?.humidity.toString() + "%"

                        var ivImage = view.findViewById<ImageView>(R.id.ivImagen)
                        var imageUrlBase = "https://openweathermap.org/img/w/"
                        Picasso.get()
                            .load(imageUrlBase + it.firstOrNull()?.weather?.firstOrNull()?.icon + ".png")
                            .into(ivImage)

                    }

                }
                launch {
                    viewModel.tiempoCincoDias.collect {
                        Log.i("GET2", it.toString())
                        if (it.isNotEmpty()) {
                            adapter?.data = it.first().list
                            adapter?.notifyDataSetChanged()
                        }
                    }
                }

                launch {
                    viewModel.loading.collect {
                        //   pbLoading.isVisible = it
                    }
                }
            }
        }
    }


}