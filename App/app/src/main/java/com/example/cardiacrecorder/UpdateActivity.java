package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<DataModel> dmArrayList;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    String dateStr, timeStr;
    DataModel record;

    EditText date,time,systolic,diastolic,heartRate,comment;
    boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        date = findViewById(R.id.upDate);
        time = findViewById(R.id.upTime);
        systolic = findViewById(R.id.upSys);
        diastolic = findViewById(R.id.upDia);
        heartRate = findViewById(R.id.hrUp);
        comment = findViewById(R.id.upComment);
        Button updateButton = findViewById( R.id.upButton);
        retrieveData();
        datePicker();
        timePicker();


        record= dmArrayList.get(index);

        date.setText(""+record.getDate());
        time.setText(""+record.getTime());
        systolic.setText(""+record.getSystolic());
        diastolic.setText(""+record.getDiastolic());
        heartRate.setText(""+record.getHeartrate());
        comment.setText(""+record.getComment());

        updateButton.setOnClickListener(v -> {
            isAllFieldsChecked = CheckAllFields();

            if(isAllFieldsChecked){
                //int sysInt = Integer.parseInt(systolic.getText().toString());
                String sysInt = systolic.getText().toString();
                //int diasInt = Integer.parseInt(diastolic.getText().toString());
                String diasInt = diastolic.getText().toString();
                //int heartInt = Integer.parseInt(heartRate.getText().toString());
                String heartInt = heartRate.getText().toString();
                String commentStr = comment.getText().toString();
                record = new DataModel(dateStr,timeStr,sysInt,diasInt,heartInt,commentStr);

                dmArrayList.set(index, record);
                HomeScreen.dmArrayList.set(index, record);
                HomeScreen.dataAdapter.notifyDataSetChanged();
                PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
                saveData();
                Toast.makeText(UpdateActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                finish();

            }




        });

    }

    private boolean CheckAllFields() {
        /*if (date.length() == 0) {
            date.setError("This field is required");
            return false;
        }

        if (time.length() == 0) {
            time.setError("This field is required");
            return false;
        }

        if (systolic.length() == 0) {
            systolic.setError("This field is required");
            return false;
        }
        String s1 = systolic.getText().toString();
        int n1 = Integer.parseInt(s1);
        if(n1<0)
        {
            systolic.setError("Invalid data input");
            return false;
        }

        if (diastolic.length() == 0) {
            diastolic.setError("This field is required");
            return false;
        }

        String s2 = diastolic.getText().toString();
        int n2 = Integer.parseInt(s2);
        if(n2<0)
        {
            diastolic.setError("Invalid data input");
            return false;
        }

        if (heartRate.length() == 0) {
            heartRate.setError("This field is required");
            return false;
        }

        String s3 = heartRate.getText().toString();
        int n3 = Integer.parseInt(s3);

        if(n3<0)
        {
            heartRate.setError("Invalid data input");
            return false;
        }*/

        // after all validation return true if all required fields are inserted.
        return true;
    }

    private void datePicker()
    {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(UpdateActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateStr = dayOfMonth + "-" + (month + 1) + "-" + year;
                date.setText(dateStr);
            }
        };
    }
    private void timePicker() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour =calendar.get(Calendar.HOUR_OF_DAY);
                int minute =calendar.get(Calendar.MINUTE);
                //calendar.clear();
                TimePickerDialog dialog =  new TimePickerDialog(UpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeStr = hourOfDay + ":" +minute;
                        time.setText(timeStr);

                    }
                }, hour , minute,true);
                //  dialog.setTitle("Select Time");

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }




    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("uday",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("record",null);
        Type type = new TypeToken<ArrayList<DataModel>>(){}.getType();
        dmArrayList = gson.fromJson(jsonString,type);
        if(dmArrayList ==null)
        {
            dmArrayList = new ArrayList<>();
        }
    }
    private void saveData()
    {
        sharedPreferences = getSharedPreferences("uday",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(dmArrayList);
        editor.putString("record",jsonString);
        editor.apply();
    }
}