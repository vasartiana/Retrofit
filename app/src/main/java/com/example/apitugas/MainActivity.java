package com.example.apitugas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txtview);

        getRandomData();
        getSuperHeroes();
    }

    private void getRandomData(){
        Call<Results> caller = RetrofitClient.getInstance().getMyApi().getRandomHero();

        caller.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                Results randomHero = response.body();
                textView.setText(randomHero.getName());

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });
    }

    private void getSuperHeroes(){
        Call<List<Results>> caller = RetrofitClient.getInstance().getMyApi().getResults();
        caller.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> heroList = response.body();
                String hero="";
                for (int i=0; i<heroList.size(); i++){
                    Results currentHero = heroList.get(i);
                    hero = hero + "," + currentHero.getName();
                }

                textView.setText(hero);

            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {

            }
        });
    }
}