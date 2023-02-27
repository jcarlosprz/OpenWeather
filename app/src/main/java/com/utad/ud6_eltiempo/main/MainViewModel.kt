package com.utad.ud6_eltiempo.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.ud6_eltiempo.data.models.TiempoActualResponse
import com.utad.ud6_eltiempo.data.models.TiempoCincoDiasResponse
import com.utad.ud6_eltiempo.data.remote.ApiRest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val tiempoActual = MutableStateFlow(listOf<TiempoActualResponse>())
    val tiempoCincoDias = MutableStateFlow(listOf<TiempoCincoDiasResponse>())

    val loading = MutableStateFlow(false)

    fun getTiempoActual() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiRest.service.getTiempoActual()
            if (response.isSuccessful && response.body() != null) {
                var listatiempo = listOf<TiempoActualResponse>(response.body()!!)
                tiempoActual.value = listatiempo
            } else {
                Log.i("GenresViewModel", "getGenres: ${response.errorBody()?.string()}")
            }

            loading.value = false

        }

    }
    fun getTiempoCincoDias() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiRest.service.getCincoDias()
            if (response.isSuccessful && response.body() != null) {
                var listatiempo = listOf<TiempoCincoDiasResponse>(response.body()!!)
                tiempoCincoDias.value = listatiempo
            } else {
                Log.i("GenresViewModel", "getGenres: ${response.errorBody()?.string()}")
            }

            loading.value = false

        }

    }
}