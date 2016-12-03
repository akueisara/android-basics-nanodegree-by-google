package com.example.android.quizapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
    private int score1, score2, score3, score4, score5, totalScore;
    private boolean checked1, checked2, checked3, checked5;
    private String anwser4;
    @BindView(R.id.button51)
    CheckBox mCheckBox1;
    @BindView(R.id.button52)
    CheckBox mCheckBox2;
    @BindView(R.id.button53)
    CheckBox mCheckBox3;
    @BindView(R.id.button54)
    CheckBox mCheckBox4;
    @BindView(R.id.edit_text_q4)
    EditText mEditTextQ4;
    @BindView(R.id.radiogroup1)
    RadioGroup mRadioGroup1;
    @BindView(R.id.radiogroup2)
    RadioGroup mRadioGroup2;
    @BindView(R.id.radiogroup3)
    RadioGroup mRadioGroup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        checked5 = mCheckBox1.isChecked() || mCheckBox2.isChecked() || mCheckBox3.isChecked() || mCheckBox4.isChecked();
    }

    public void clickSubmit(View view) {
        anwser4 = mEditTextQ4.getText().toString();
        if(checked1 && checked2 && checked3 && !anwser4.equals("") && checked5 ) {

            // count question 4
            if(anwser4.toLowerCase().equals("java"))
                score4 = 20;
            else
                score4 = 0;

            // count question 5
            score5 = 0;
            if(mCheckBox1.isChecked()) {
                score5 += -10;
            }
            if(mCheckBox2.isChecked()) {
                score5 += 10;
            }
            if(mCheckBox3.isChecked()) {
                score5 += 10;
            }
            if(mCheckBox4.isChecked()) {
                score5 += -10;
            }

            if (score5 < 0) {
                score5 = 0;
            }

            totalScore = score1 + score2 + score3 + score4 + score5;
            String anwser = getString(R.string.your_score_is) + "  " + totalScore;
            Toast.makeText(getApplicationContext(), anwser, Toast.LENGTH_LONG).show();
        }
        else {
            Context context = getApplicationContext();
            String text = getString(R.string.make_sure_answer_questions);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void clickReset(View view) {
        uncheckRadioGroup(mRadioGroup1);
        uncheckRadioGroup(mRadioGroup2);
        uncheckRadioGroup(mRadioGroup3);
        mEditTextQ4.setText("");
        uncheckCheckBoxes();
    }

    private void uncheckRadioGroup(RadioGroup radioGroup) {
        radioGroup.clearCheck();
    }

    private void uncheckCheckBoxes() {
        mCheckBox1.setChecked(false);
        mCheckBox2.setChecked(false);
        mCheckBox3.setChecked(false);
        mCheckBox4.setChecked(false);
        checked5 = false;
    }
}
