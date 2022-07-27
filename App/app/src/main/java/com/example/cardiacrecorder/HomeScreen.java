package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    static ArrayList<DataModel> dmArrayList;
    DataModel dataModel;

    RecyclerView recyclerView;

    DataAdapter.RecyclerViewClickListener listener;

    static DataAdapter dataAdapter;
    LinearLayoutManager linearLayoutManager;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    FloatingActionButton addButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setOnClickListener();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
      //  dmArrayList= new ArrayList<>();
       // dataModel = new DataModel("12-11-12","11:21","120","80","60","sit");
        //dmArrayList.add(dataModel);
       // dmArrayList.add(new DataModel("12-1-12","11:21","120","80","60","sit"));
        retrieveData();

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(HomeScreen.this,LinearLayoutManager.VERTICAL,false);
        dataAdapter = new DataAdapter(HomeScreen.this, listener);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dataAdapter);



        addButton= findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, AddActivity.class));
            }
        }




        );



          //  public DataModel(String date, String time, String systolic, String diastolic, String heartrate, String comment) {


        }

    private void setOnClickListener() {
        listener = new DataAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        };
    }







    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("uday",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("record",null);
        Type type = new TypeToken<ArrayList<DataModel>>(){}.getType();
        RecordList.myArraylist = gson.fromJson(jsonString,type);
        if(RecordList.myArraylist ==null)
        {
            RecordList.myArraylist = new ArrayList<>();
        }
    }
    private void saveData()
    {
        sharedPreferences = getSharedPreferences("uday",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(RecordList.myArraylist);
        editor.putString("record",jsonString);
        editor.apply();
    }
}