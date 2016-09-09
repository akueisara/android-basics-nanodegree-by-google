package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTextView(R.id.solution1, "solution1");
        setTextView(R.id.solution2, "solution2");
        setTextView(R.id.solution3, "solution3");
        setTextView(R.id.solution4, "solution4");
        setTextView(R.id.solution5, "solution5");
    }

    private void setTextView(int id, String extra) {
        String solution = super.getIntent().getExtras().getString(extra);
        TextView textView = (TextView) findViewById(id);
        textView.setText(solution);
    }

    public void clickBack(View view) {
        finish();
    }
}
