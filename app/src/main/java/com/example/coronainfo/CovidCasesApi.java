package com.example.coronainfo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidCasesApi {

    @GET("updates")
    Call<List<Earth>> getPost();

    @GET("cases")
    Call<List<Earth>> getCases();
}
