package org.hse.timetableforhsefe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View buttonStudent = findViewById(R.id.btnStd);
        View buttonTeacher = findViewById(R.id.btnTch);
        View settingsButton = findViewById(R.id.btnSettings);
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
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
}