package com.utad.ud6_eltiempo.data.remote


import com.utad.ud6_eltiempo.data.models.TiempoActualResponse
import com.utad.ud6_eltiempo.data.models.TiempoCincoDiasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather?")
    suspend fun getTiempoActual(
        @Query("lat") lat: Int = ApiRest.lat,
        @Query("lon") lon: Int = ApiRest.lon,
        @Query("lang") lang: String = ApiRest.lang,
        @Query("units") units: String = ApiRest.units,
        @Query("appid") appid: String = ApiRest.appid,
    ): Response<TiempoActualResponse>

    @GET("data/2.5/forecast?")
    suspend fun getCincoDias(
        @Query("lat") lat: Int = ApiRest.lat,
        @Query("lon") lon: Int = ApiRest.lon,
        @Query("lang") lang: String = ApiRest.lang,
        @Query("units") units: String = ApiRest.units,
        @Query("appid") appid: String = ApiRest.appid,
    ): Response<TiempoCincoDiasResponse>

}
