package com.example.cricketscore.utils;

import com.example.cricketscore.models.CricketDataModel;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiInterface {

    @GET("v1/f7f771d8-52d5-4ac6-bdaf-858c946cd212")
    Call<CricketDataModel> getMatchData();
}
