package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int score1, score2, score3, score4, score5, totalScore;
    private boolean checked1, checked2, checked3, checked5;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private String anwser4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checked5 = false;
        checkBox1 = (CheckBox) findViewById(R.id.button51);
        checkBox2 = (CheckBox) findViewById(R.id.button52);
        checkBox3 = (CheckBox) findViewById(R.id.button53);
        checkBox4 = (CheckBox) findViewById(R.id.button54);
    }

    public void onCheckedQuestion1(View view) {
        checked1 = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button11:
                if (checked1) {
                    score1 = 0;
                    break;
                }
            case R.id.button12:
                if (checked1) {
                    score1 = 20;
                    break;
                }
            case R.id.button13:
                if (checked1) {
                    score1 = 0;
                    break;
                }
        }
    }

    public void onCheckedQuestion2(View view) {
        checked2 = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button21:
                if (checked2) {
                    score2 = 20;
                    break;
                }
            case R.id.button22:
                if (checked2) {
                    score2 = 0;
                    break;
                }
            case R.id.button23:
                if (checked2) {
                    score2 = 0;
                    break;
                }
        }
    }

    public void onCheckedQuestion3(View view) {
        checked3 = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button31:
                if (checked3) {
                    score3 = 0;
                    break;
                }
            case R.id.button32:
                if (checked3) {
                    score3 = 0;
                    break;
                }
            case R.id.button33:
                if (checked3) {
                    score3 = 20;
                    break;
                }
        }
    }

    public void onCheckedQuestion5(View view) {
        checked5 = checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked();
    }

    public void clickSubmit(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_text_q4);
        anwser4 = editText.getText().toString();
        if(checked1 && checked2 && checked3 && !anwser4.equals("") && checked5 ) {

            // count question 4
            if(anwser4.toLowerCase().equals("java"))
                score4 = 20;
            else
                score4 = 0;

            // count question 5
            score5 = 0;
            if(checkBox1.isChecked()) {
                score5 += -10;
            }
            if(checkBox4.isChecked()) {
                score5 += -10;
            }

            if(checkBox2.isChecked()) {
                score5 += 10;
            }
            if(checkBox3.isChecked()) {
                score5 += 10;
            }
            if (score5 < 0) {
                score5 = 0;
            }

            totalScore = score1 + score2 + score3 + score4 + score5;
            String anwser = "Your Score is  " + totalScore;
            Toast.makeText(getApplicationContext(), anwser, Toast.LENGTH_LONG).show();
        }
        else {
            Context context = getApplicationContext();
            String text = "Please make sure you answer all the questions";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void clickReset(View view) {
        uncheckRadioGroup(R.id.radiogroup1);
        uncheckRadioGroup(R.id.radiogroup2);
        uncheckRadioGroup(R.id.radiogroup3);
        EditText editText = (EditText) findViewById(R.id.edit_text_q4);
        editText.setText("");
        uncheckCheckBoxes();

    }

    private void uncheckRadioGroup(int id) {
        RadioGroup radioGroup = (RadioGroup)findViewById(id);
        radioGroup.clearCheck();
    }

    private void uncheckCheckBoxes() {
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.button51);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.button52);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.button53);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.button54);
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
    }
}
