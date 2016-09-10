package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String result1, result2, result3, result4, result5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheckedQuestion1(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button11:
                if (checked) {
                    result1 = "Your ideal mate has a sense of humor and is lively.";
                    break;
                }
            case R.id.button12:
                if (checked) {
                    result1 = "Your ideal mate has a good figure.";
                    break;
                }
            case R.id.button13:
                if (checked) {
                    result1 = "Your ideal mate is gentle and sweet.";
                    break;
                }
            case R.id.button14:
                if (checked) {
                    result1 = "Your ideal mate is kind and generous.";
                    break;
                }
            case R.id.button15:
                if (checked) {
                    result1 = "Your ideal mate is calm and reliable.";
                    break;
                }
        }
    }

    public void onCheckedQuestion2(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button21:
                if (checked) {
                    result2 = "Most of your plan would be successful. When you wish, you make a reasonable wish.";
                    break;
                }
            case R.id.button22:
                if (checked) {
                    result2 = "You always compare yourself with others. You make your wishes too difficult to come true.";
                    break;
                }
            case R.id.button23:
                if (checked) {
                    result2 = "About your wish, you always want to be with your friends.";
                    break;
                }
            case R.id.button24:
                if (checked) {
                    result2 = "You like changes. Going for a trip is challenging to you.";
                    break;
                }
            case R.id.button25:
                if (checked) {
                    result2 = "Good rest and great food make you feel happy more than anything else.";
                    break;
                }
        }
    }

    public void onCheckedQuestion3(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button31:
                if (checked) {
                    result3 = "Success depends on someone's faith in their ability. That's your attitudes towards success.";
                    break;
                }
            case R.id.button32:
                if (checked) {
                    result3 = "No effort, no success. That's your attitudes towards success.";
                    break;
                }
            case R.id.button33:
                if (checked) {
                    result3 = "You are worried and uncertain about your success. That's your attitudes towards success.";
                    break;
                }
            case R.id.button34:
                if (checked) {
                    result3 = "You are in despair about your lack of success. That's your attitudes towards success.";
                    break;
                }
            case R.id.button35:
                if (checked) {
                    result3 = "Success or failure, that's no big deal. You don't care. That's your attitudes towards success.";
                    break;
                }
        }
    }

    public void onCheckedQuestion4(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button41:
                if (checked) {
                    result4 = "You are a person of principle. You respect social rules and regulations.";
                    break;
                }
            case R.id.button42:
                if (checked) {
                    result4 = "You realize that there are always ups and downs. Joy and sorrow are constant companions.";
                    break;
                }
            case R.id.button43:
                if (checked) {
                    result4 = "Can a woman be president? You will answer no to this question.";
                    break;
                }
            case R.id.button44:
                if (checked) {
                    result4 = "You think that if you like what you are and have, then no matter what, you will always be happy.";
                    break;
                }
            case R.id.button45:
                if (checked) {
                    result4 = "You love freedom and don't want to get tied down.";
                    break;
                }
        }
    }

    public void onCheckedQuestion5(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.button51:
                if (checked) {
                    result5 = "You are emotional, sincere and optimistic.";
                    break;
                }
            case R.id.button52:
                if (checked) {
                    result5 = "You are able to adapt and respond to changes. You think life is always uncertain.";
                    break;
                }
            case R.id.button53:
                if (checked) {
                    result5 = "Be more rational, and try to control your emotions.";
                    break;
                }
            case R.id.button54:
                if (checked) {
                    result5 = "You are strong-willed, and tend to be a little self-centered.";
                    break;
                }
            case R.id.button55:
                if (checked) {
                    result5 = "You think very positively, and believe in yourself.";
                    break;
                }
        }
    }

    public void clickSubmit(View view) {
        if(result1 != null && result2!= null && result3 != null && result4 != null && result5 != null) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("solution1", "1. " + result1);
            intent.putExtra("solution2", "2. " + result2);
            intent.putExtra("solution3", "3. " + result3);
            intent.putExtra("solution4", "4. " + result4);
            intent.putExtra("solution5", "5. " + result5);
            startActivity(intent);
        }
        else {
            Context context = getApplicationContext();
            String text = "Please answer all the questions";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void clickReset(View view) {
        uncheckRadioGroup(R.id.radiogroup1);
        uncheckRadioGroup(R.id.radiogroup2);
        uncheckRadioGroup(R.id.radiogroup3);
        uncheckRadioGroup(R.id.radiogroup4);
        uncheckRadioGroup(R.id.radiogroup5);
        result1 = null;
        result2 = null;
        result3 = null;
        result4 = null;
        result5 = null;
    }

    private void uncheckRadioGroup(int id) {
        RadioGroup radioGroup = (RadioGroup)findViewById(id);
        radioGroup.clearCheck();
    }
}
