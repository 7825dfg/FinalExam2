package com.example.a403.finalexam;


import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;
    Switch switch1;
    LinearLayout LL1, LL2;
    Chronometer Cmeter;
    RadioGroup radioGroup, radioGroup2;
    RadioButton rb1, rb2, rb3;
    ImageView imageView;
    EditText editText1, editText2, editText3;
    TextView tview1, tview2, tview3;
    TimePicker timePicker;
    CalendarView calendarView;
    String num1, num2, num3;
    double result, total;
    int count;
    int ryear, rmonth,rday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("놀이동산 예약시스템");

        switch1 = (Switch)findViewById(R.id.Switch);
        LL1 = (LinearLayout)findViewById(R.id.LL1);
        LL2 = (LinearLayout)findViewById(R.id.LL2);
        Cmeter = (Chronometer)findViewById(R.id.chronometer);
        radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        radioGroup2 = (RadioGroup)findViewById(R.id.RadioGroup2);
        imageView = (ImageView)findViewById(R.id.imageView);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        rb1 = (RadioButton)findViewById(R.id.radioButton);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);
        tview1 = (TextView)findViewById(R.id.tView5);
        tview2 = (TextView)findViewById(R.id.tView6);
        tview3 = (TextView)findViewById(R.id.tView7);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        LL1.setVisibility(View.INVISIBLE);
        LL2.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    Cmeter.setBase(SystemClock.elapsedRealtime());
                    Cmeter.start();
                    Cmeter.setTextColor(Color.BLUE);
                    LL1.setVisibility(View.VISIBLE);
                }
                else{
                    Cmeter.setBase(SystemClock.elapsedRealtime());
                    Cmeter.stop();
                    Cmeter.setTextColor(Color.BLACK);
                    LL1.setVisibility(View.INVISIBLE);
                    LL2.setVisibility(View.INVISIBLE);
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    tview1.setText("총 명수 : ");
                    tview2.setText("할인금액 : ");
                    tview3.setText("결제금액 : ");
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group.getId()==R.id.RadioGroup){
                    switch (checkedId){
                        case R.id.radioButton :
                            imageView.setImageResource(R.drawable.pic1);
                            break;
                        case R.id.radioButton2 :
                            imageView.setImageResource(R.drawable.pic2);
                            break;
                        case R.id.radioButton3 :
                            imageView.setImageResource(R.drawable.pic3);
                    }
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = editText1.getText().toString();
                num2 = editText2.getText().toString();
                num3 = editText3.getText().toString();

                if(num1.isEmpty() || num2.isEmpty() || num3.isEmpty()){
                    Toast.makeText(getApplicationContext(), "인원을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                total = (Integer.parseInt(num1)*15000)+(Integer.parseInt(num2)*12000)+ (Integer.parseInt(num3)*8000);
                count = Integer.parseInt(num1)+Integer.parseInt(num2)+Integer.parseInt(num3);

                if(rb1.isChecked()){
                    result = total * 0.95;
                }
                else if(rb2.isChecked()){
                    result = total * 0.9;
                }
                else if (rb3.isChecked()){
                    result = total * 0.8;
                }

                tview1.setText("총 명수 : "+count);
                tview2.setText(String.format("할인금액 : %.0f", result));
                tview3.setText(String.format("결제금액 : %.0f", total));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LL1.setVisibility(View.INVISIBLE);
                LL2.setVisibility(View.VISIBLE);
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getId() == R.id.RadioGroup2) {
                    switch (checkedId) {
                        case R.id.radioButton4:
                            calendarView.setVisibility(View.VISIBLE);
                            timePicker.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.radioButton5:
                            calendarView.setVisibility(View.INVISIBLE);
                            timePicker.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                ryear= year;
                rmonth = month;
                rday = dayOfMonth;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "인원예약을 먼저하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), ryear + "년" + rmonth + "월" + rday + "일" + timePicker.getCurrentHour() + "시 " + timePicker.getCurrentMinute() + "분 예약완료", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LL1.setVisibility(View.VISIBLE);
                LL2.setVisibility(View.INVISIBLE);
            }
        });
    }
}