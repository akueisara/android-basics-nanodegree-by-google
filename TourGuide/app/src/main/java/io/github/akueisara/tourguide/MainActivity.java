package io.github.akueisara.tourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView taipei = (TextView) findViewById(R.id.taipei);
        taipei.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent taipeiIntent = new Intent(MainActivity.this, TaipeiActivity.class);
                startActivity(taipeiIntent);
            }
        });

        TextView taichung = (TextView) findViewById(R.id.taichung);
        taichung.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent taichungIntent = new Intent(MainActivity.this, TaichungActivity.class);
                startActivity(taichungIntent);
            }
        });

        TextView tainan = (TextView) findViewById(R.id.tainan);
        tainan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tainanIntent = new Intent(MainActivity.this, TainanActivity.class);
                startActivity(tainanIntent);
            }
        });

        TextView kaohsiung = (TextView) findViewById(R.id.kaohsiung);
        kaohsiung.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kaohsiungIntent = new Intent(MainActivity.this, KaohsiungActivity.class);
                startActivity(kaohsiungIntent);
            }
        });
    }
}
