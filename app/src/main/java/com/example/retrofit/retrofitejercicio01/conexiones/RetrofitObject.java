package com.example.retrofit.retrofitejercicio01.conexiones;

import com.example.retrofit.retrofitejercicio01.config.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    public static Retrofit getConexion(){
        return new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
