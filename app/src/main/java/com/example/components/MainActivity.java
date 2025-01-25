package com.example.components;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textSelectedDateTime;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textSelectedDateTime = findViewById(R.id.text_selected_date_time);
        Button buttonDatePicker = findViewById(R.id.button_date_picker);
        Button buttonTimePicker = findViewById(R.id.button_time_picker);
        fab = findViewById(R.id.fab);
        progressBar = findViewById(R.id.progress_bar);

        buttonDatePicker.setOnClickListener(v -> showDatePicker());
        buttonTimePicker.setOnClickListener(v -> showTimePicker());

        fab.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
        });

        progressBar.setVisibility(View.VISIBLE);
        new android.os.Handler().postDelayed(() -> progressBar.setVisibility(View.GONE), 3000);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            textSelectedDateTime.setText("Selected Date: " + date);
        }, year, month, day).show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            String formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
            textSelectedDateTime.setText("Selected Time: " + formattedTime);
        }, hour, minute, true).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            SpannableString spannable = new SpannableString(menuItem.getTitle());
            spannable.setSpan(
                    new ForegroundColorSpan(Color.BLACK), 0, spannable.length(), 0);
            menuItem.setTitle(spannable);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_date_time_picker) {
            Toast.makeText(this, "Date & Time Picker Selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menu_fab) {
            Toast.makeText(this, "FAB Selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menu_progress_bar) {
            Toast.makeText(this, "Progress Bar Selected", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}