package io.github.akueisara.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import io.github.akueisara.habittracker.data.HabitContract.HabitEntry;
import io.github.akueisara.habittracker.data.HabitDbHelper;


public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new HabitDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabase();
    }

    private Cursor read() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_NAME,
                HabitEntry.COLUMN_START_DATE,
                HabitEntry.COLUMN_NUMBER_OF_TIMES
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    private void displayDatabase() {
        Cursor cursor = read();

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {
            displayView.setText("The habit table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_NAME + " - " +
                    HabitEntry.COLUMN_START_DATE + " - " +
                    HabitEntry.COLUMN_NUMBER_OF_TIMES + " - " + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_START_DATE);
            int numberOfTimesColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NUMBER_OF_TIMES);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                int currentNumberOfTimes = cursor.getInt(numberOfTimesColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDate + " - " +
                        currentNumberOfTimes));
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void insert() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        values1.put(HabitEntry.COLUMN_NAME, "Drink water");
        values1.put(HabitEntry.COLUMN_NUMBER_OF_TIMES, 2);
        values2.put(HabitEntry.COLUMN_NAME, "Walk my dog");
        values2.put(HabitEntry.COLUMN_NUMBER_OF_TIMES, 1);
        db.insert(HabitEntry.TABLE_NAME, null, values1);
        db.insert(HabitEntry.TABLE_NAME, null, values2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insert();
                displayDatabase();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
