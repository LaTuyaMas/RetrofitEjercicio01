package com.example.retrofit.retrofitejercicio01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofit.retrofitejercicio01.adapters.ResponseAdapter;
import com.example.retrofit.retrofitejercicio01.conexiones.ApiConexiones;
import com.example.retrofit.retrofitejercicio01.conexiones.RetrofitObject;
import com.example.retrofit.retrofitejercicio01.databinding.ActivityMainBinding;
import com.example.retrofit.retrofitejercicio01.models.Response;
import com.example.retrofit.retrofitejercicio01.models.User;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Response response;
    private List<User> usersList;
    private ResponseAdapter adapter;
    private RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usersList = new ArrayList<>();
        response = new Response();
        adapter = new ResponseAdapter(usersList, R.layout.user_view_holder, this);
        lm = new LinearLayoutManager(this);

        binding.contenedor.setAdapter(adapter);
        binding.contenedor.setLayoutManager(lm);

        doGetUsers();
    }

    private void doGetUsers() {
        Retrofit retrofit = RetrofitObject.getConexion();
        ApiConexiones conexiones = retrofit.create(ApiConexiones.class);

        Call<Response> users = conexiones.getUsers();

        users.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Response temp = response.body();
                    assert temp != null;
                    usersList.addAll(temp.getData());
                    adapter.notifyItemRangeInserted(0, temp.getData().size());
                    for (User u : usersList) {
                        Log.d("USER", "onResponse: " + u.toString());
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, response.code() + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR, NO TIENES INTERNET", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }
}