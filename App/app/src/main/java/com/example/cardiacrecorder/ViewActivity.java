package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

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

            dateText.setText(HomeScreen.dmArrayList.get(extras).getDate());
            timeText.setText(HomeScreen.dmArrayList.get(extras).getTime());
            sysText.setText(HomeScreen.dmArrayList.get(extras).getSystolic());
            diaText.setText(HomeScreen.dmArrayList.get(extras).getDiastolic());
            hrText.setText(HomeScreen.dmArrayList.get(extras).getHeartrate());
            commentText.setText(HomeScreen.dmArrayList.get(extras).getComment());
        }

        
    }
}