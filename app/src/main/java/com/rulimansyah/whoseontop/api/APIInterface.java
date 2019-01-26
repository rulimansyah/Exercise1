package com.rulimansyah.whoseontop.api;

import com.rulimansyah.whoseontop.model.Model;
import com.rulimansyah.whoseontop.model.Value;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("joke")
    Call<Value> getJoke();

    @GET("type")
    Call<Model> getType();

    @GET("value")
    Call<Model> getValue();
}
