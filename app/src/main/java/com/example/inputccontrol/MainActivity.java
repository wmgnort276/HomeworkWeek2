package com.example.inputccontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean state;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mssv,name,cccd,phone,email;
    private CheckBox agree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mssv = findViewById(R.id.editMssv);
        name = findViewById(R.id.editName);
        cccd = findViewById(R.id.editCCCD);
        phone = findViewById(R.id.editPhoneNumber);
        email = findViewById(R.id.editEmail);
        agree = findViewById(R.id.agree);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }


    public void handleFinish(View view) {
        if(mssv.length() == 0 ||
                name.length() == 0 || cccd.length() == 0 ||
                phone.length() == 0 || email.length() == 0 || !agree.isChecked())
        {
            Toast toast = Toast.makeText(this, "Some fields have not been filled yet!", Toast.LENGTH_LONG);
            toast.show();
            if(mssv.length() == 0) mssv.requestFocus();
            else if(name.length() == 0) name.requestFocus();
            else if(cccd.length() == 0) cccd.requestFocus();
            else if(phone.length() == 0) phone.requestFocus();
            else if(email.length() == 0) email.requestFocus();
            else if(!agree.isChecked()) {
                Toast agreeToast = Toast.makeText(this, "Agree not checked!", Toast.LENGTH_LONG);
                agreeToast.show();
            }
        }
        else {
            Toast toast = Toast.makeText(this, "Completed!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}