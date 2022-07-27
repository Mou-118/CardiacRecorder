package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<DataModel> myArrayList = RecordList.myArraylist;
    DataModel dataModel;


    /**
     * It shows the measurements
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        TextView dateText, timeText, sysText, diaText, hrText, commentText;
        dateText = findViewById(R.id.dateView);
        timeText=findViewById(R.id.timeView);
        sysText=findViewById(R.id.sysView);
        diaText=findViewById(R.id.diaView);
        hrText=findViewById(R.id.hrView);
        commentText=findViewById(R.id.commentView);

        String dt, tt, st, diaT, hrt, ct;
        int extras= getIntent().getIntExtra("position",-1);
        if(extras>=0)
        {
            retrieveData();
            dataModel=myArrayList.get(extras);

            dateText.setText(myArrayList.get(extras).getDate());
            timeText.setText(myArrayList.get(extras).getTime());
            sysText.setText(myArrayList.get(extras).getSystolic());
            diaText.setText(myArrayList.get(extras).getDiastolic());
            hrText.setText(myArrayList.get(extras).getHeartrate());
            commentText.setText(myArrayList.get(extras).getComment());
        }

        
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
}