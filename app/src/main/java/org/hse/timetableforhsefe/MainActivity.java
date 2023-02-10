package org.hse.timetableforhsefe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.hse.timetableforhsefe.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnStdClick(View view){
        Intent intent = new Intent(this,StudentActivity.class);
        startActivity(intent);
    }

    public void onBtnTchClick(View view){
        Intent intent = new Intent(this,TeacherActivity.class);
        startActivity(intent);
    }

    public void onBtnSettingsClick(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}