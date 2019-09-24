package com.emeryc.repos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emeryc.repos.http.RetrofitUtil;
import com.emeryc.repos.http.Service;
import com.emeryc.repos.transfer.Repos;
import com.emeryc.repos.transfer.Utilisateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        final Service service = RetrofitUtil.get();
        final EditText et = findViewById(R.id.edit);
        final Button b = findViewById(R.id.btn);
        final TextView t = findViewById(R.id.txt);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = et.getText().toString();

                //Username
                service.utilisateur(nom).enqueue(new Callback<Utilisateur>() {
                    @Override
                    public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                        if (response.isSuccessful())
                        {
                            t.setText(response.body().login + " possède " + response.body().followers + " followers!");

                        } else {
                            Log.i("Retrofit: ", response.code() + "");
                        }
                    }

                    @Override
                    public void onFailure(Call<Utilisateur> call, Throwable t) {

                    }
                });

                //ListRepos
                service.listRepos(nom).enqueue(new Callback<List<Repos>>() {
                    @Override
                    public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                        if (response.isSuccessful())
                        {
                            List<Repos> r = response.body();
                            mAdapter = new Adapteur(r, getApplicationContext());
                            recyclerView.setAdapter(mAdapter);
                        } else {
                            Log.i("Retrofit: ", response.code() + "");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repos>> call, Throwable t) {
                        Log.i("Retrofit: ", t.getMessage());
                    }
                });

            }
        });

    }
}
