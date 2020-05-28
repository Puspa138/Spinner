package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        //call the bank server to retrieve
        Retrofit retrofit = new Retrofit.Builder ().
                baseUrl("http://192.168.42.227:8080/AdhaarApi/")
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();
            BankApi bankApi = retrofit.create(BankApi.class);

        Call<List<Bank>> call = bankApi.getBank();
        call.enqueue(new Callback<List<Bank>>() {
            @Override
            public void onResponse(Call<List<Bank>> call, Response<List<Bank>> response) {
                if (!response.isSuccessful ()){
                    Toast.makeText (getApplicationContext (), " Mint Server is not responding "+response.code (), Toast.LENGTH_LONG).show ();
                    return;
                }
                List<Bank> banks = response.body();

                for (Bank  report: banks){


                }
            }

            @Override
            public void onFailure(Call<List<Bank>> call, Throwable t) {

            }
        });



        // Spinner Drop down elements
        List<String> bank = new ArrayList<String>();
        bank.add("170301120138");
        bank.add("170301120142");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bank);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected bank:" + item, Toast.LENGTH_LONG).show();
        String data = item;



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}