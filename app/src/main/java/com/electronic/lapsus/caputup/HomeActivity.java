package com.electronic.lapsus.caputup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    final int POLITIC_CATEGORY = 1;
    final int RIDDLES_CATEGORY = 2;
    final int SUPERSTARS_CATEGORY = 3;
    final int ANIMALS_CATEGORY = 4;
    final int SONGS_CATEGORY = 5;
    final int MOVIES_CATEGORY = 6;

    static List<Category> categoryList = new ArrayList<>();
    ListView listView;
    CustomListAdapter customListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        customListAdapter = new CustomListAdapter(this, categoryList);
        listView = (ListView) findViewById(R.id.categoriesList);

        listView.setAdapter(customListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Category category_selected = categoryList.get(position);

                Toast.makeText(getApplicationContext(), category_selected.getTitle() , Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                intent.putExtra("category", category_selected);
                startActivityForResult(intent, category_selected.getId());
            }
        });

    }

    public List<Category> getCategories(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriesService categoriesService = retrofit.create(CategoriesService.class);
        Call<List<Category>> call = categoriesService.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                for(Category category : response.body()){
                    categoryList.add(
                            new Category(category.getId(), category.getTitle(), category.getWords(), category.getImageUrl()));
                }
             //   customListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

        return categoryList;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String category = data.getStringExtra("category");

        if(resultCode == RESULT_OK){

        }

    }


}
