package com.example.maciej1.news.ui.sources;

import android.util.Log;

import com.example.maciej1.news.data.ApiClient;
import com.example.maciej1.news.data.ApiInterface;
import com.example.maciej1.news.data.SourceEntry;
import com.example.maciej1.news.data.ApiResponse;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SourcesPresenter extends MvpBasePresenter<SourcesView> {

    private static final String API_KEY = "c170c634ec2a4381aac741f46d9aee4d";


    public void startApiService() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ApiResponse> call = apiService.getEnglishSources(API_KEY);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<SourceEntry> sourceEntries = response.body().getSources();
                getView().showSources(sourceEntries);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("onFailure: ", t.toString());
            }
        });
    }


    public void startArticlesFragment(String id) {
        getView().inflateArticlesFragment(id);
    }
}
