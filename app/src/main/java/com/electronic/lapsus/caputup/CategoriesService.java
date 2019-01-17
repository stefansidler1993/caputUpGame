package com.electronic.lapsus.caputup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesService {

    String API_ROUTE = "/bins/8qey0";

    @GET(API_ROUTE)
    Call<List<Category>> getCategories();



}
