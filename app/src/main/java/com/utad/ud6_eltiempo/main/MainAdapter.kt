package com.utad.ud6_eltiempo.main

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utad.ud6_eltiempo.R
import com.utad.ud6_eltiempo.data.models.TiempoCincoDiasResponse
import java.util.*


class MainAdapter(var data: List<TiempoCincoDiasResponse.WeatherItem> = listOf()) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var datos = data.get(position)
        holder.bind(datos)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTemperatura = itemView.findViewById<TextView>(R.id.tvDiaTemperatura)
        var tvHumedad = itemView.findViewById<TextView>(R.id.tvDiaHumedad)
        var ivIcon = itemView.findViewById<ImageView>(R.id.ivIcon)
        var tvFecha = itemView.findViewById<TextView>(R.id.tvFecha)


        var imageUrlBase = "https://openweathermap.org/img/w/"

        fun bind(item: TiempoCincoDiasResponse.WeatherItem) {
            tvTemperatura.text = item.main.temp.toString() + " ºC"
            tvHumedad.text = "Humedad: " + item.main.humidity.toString() + "%"
            Picasso.get().load(imageUrlBase + item.weather[0].icon + ".png").into(ivIcon)
            tvFecha.text = formatDate(item.dt_txt)
        }
    }

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d 'de' MMMM, HH:mm", Locale("es", "ES"))

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }


}
