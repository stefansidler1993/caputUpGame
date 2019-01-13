package com.electronic.lapsus.caputup;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    final int POLITIC_CATEGORY = 1;
    final int RIDDLES_CATEGORY = 2;
    final int SUPERSTARS_CATEGORY = 3;
    final int ANIMALS_CATEGORY = 4;
    final int SONGS_CATEGORY = 5;
    final int MOVIES_CATEGORY = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView img_politic = null;
        ImageView img_riddle = null;
        ImageView img_superstars = null;
        ImageView img_animals = null;
        ImageView img_songs = null;
        ImageView img_movies = null;

        img_politic = findViewById(R.id.tb_politics_category);
        img_riddle = findViewById(R.id.tb_riddles_category);
        img_superstars = findViewById(R.id.tb_superstars_category);
        img_animals = findViewById(R.id.tb_animals_category);
        img_songs = findViewById(R.id.tb_songs_category);
        img_movies = findViewById(R.id.tb_movies_category);

        img_politic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Política", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("category", "politic");
                startActivityForResult(intent, POLITIC_CATEGORY);

            }
        });

        img_riddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Adivinanzas", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("category", "riddles");
                startActivityForResult(intent, RIDDLES_CATEGORY);}
        });

        img_superstars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Famosos", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("category", "superstars");
                startActivityForResult(intent, SUPERSTARS_CATEGORY);
            }
        });

        img_animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Animales", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("category", "animals");
                startActivityForResult(intent, ANIMALS_CATEGORY);
            }
        });

        img_songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Canciones", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("category", "songs");
                startActivityForResult(intent, SONGS_CATEGORY);
            }
        });

        img_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Peliculas", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("category", "movies");
                startActivityForResult(intent, MOVIES_CATEGORY);
           }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String category = data.getStringExtra("category");

        if(requestCode == POLITIC_CATEGORY){
            if(resultCode == RESULT_OK){

            }
        } else if(requestCode == RIDDLES_CATEGORY){
            if(resultCode == RESULT_OK){

            }
        } else if(requestCode == SUPERSTARS_CATEGORY){
            if(resultCode == RESULT_OK){

            }
        } else if(requestCode == ANIMALS_CATEGORY){
            if(resultCode == RESULT_OK){

            }
        } else if(requestCode == SONGS_CATEGORY){
            if(resultCode == RESULT_OK){

            }
        } else if(requestCode == MOVIES_CATEGORY){
            if(resultCode == RESULT_OK){

            }
        }

    }


}
