package com.example.retrofit.retrofitejercicio01.conexiones;

import com.example.retrofit.retrofitejercicio01.models.Response;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConexiones {

    @GET("/api/users")
    Call<Response> getUsers();
}
