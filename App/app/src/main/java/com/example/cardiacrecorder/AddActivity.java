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

public class AddActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    ArrayList<DataModel>recordArraylist;
    DataModel dataModel;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    EditText date,time,diastolic, systolic,heartrate, comment;
    Button saveButton;
    String dateStr, timeStr;
    boolean isAllFieldsChecked=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        date = findViewById(R.id.etDate);
        time = findViewById(R.id.etTime);
        systolic = findViewById(R.id.etSystolic);
        diastolic = findViewById(R.id.etDiastolic);
        heartrate = findViewById(R.id.etRate);
        comment=findViewById(R.id.etComment);
        saveButton = findViewById(R.id.addButton);
        retrieveData();
        //datePicker();
        timePicker();
/**
 * OnClickListener for date
 */
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar= Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(AddActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

/**
 * It sets the date on dateStr
 */
        onDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateStr = dayOfMonth + "-" + (month + 1) + "-" + year;
                date.setText(dateStr);
            }
        };
        /**
         * It defines the method of savebutton
         */
        saveButton.setOnClickListener(v->{

            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {

                //String timeStr = time.getText().toString();
                String sysInt = systolic.getText().toString();
                String diasInt = diastolic.getText().toString();
                String heartInt = heartrate.getText().toString();
                String commentStr = comment.getText().toString();

                dataModel = new DataModel(dateStr,timeStr,sysInt,diasInt,heartInt,commentStr);

                recordArraylist.add(dataModel);
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
                saveData();

                Intent i = new Intent(AddActivity.this, HomeScreen.class);
                startActivity(i);
                finish();
            }
        });



    }

    /**
     * It checks whether all fields are correct or not
     * @return
     */
    private boolean CheckAllFields()
    {

        return true;
    }

    /**
     * It picks the time
     */
    private void timePicker() {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour =calendar.get(Calendar.HOUR_OF_DAY);
                int minute =calendar.get(Calendar.MINUTE);
                //calendar.clear();
                TimePickerDialog dialog =  new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

    /**
     * Function for retrieving data
     */
    private void retrieveData()
    {
        sharedPreferences = getSharedPreferences("uday",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("record",null);
        Type type = new TypeToken<ArrayList<DataModel>>(){}.getType();
        recordArraylist = gson.fromJson(jsonString,type);
        if(recordArraylist ==null)
        {
            recordArraylist = new ArrayList<>();
        }
    }

    /**
     * Function for saving data
     */
    private void saveData()
    {
        sharedPreferences = getSharedPreferences("uday",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String jsonString = gson.toJson(recordArraylist);
        editor.putString("record",jsonString);
        editor.apply();
    }
}