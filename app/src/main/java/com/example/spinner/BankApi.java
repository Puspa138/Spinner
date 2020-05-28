package com.example.spinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BankApi {
    @GET("bankName")
    Call<List<Bank>> getBank();


}
