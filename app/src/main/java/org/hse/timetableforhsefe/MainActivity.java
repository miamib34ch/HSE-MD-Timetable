package org.hse.timetableforhsefe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View buttonStudent = findViewById(R.id.btnStd);
        View buttonTeacher = findViewById(R.id.btnTch);
    }

    public void onBtnStdClick(View view){
        Intent intent = new Intent(this,StudentActivity.class);
        startActivity(intent);
    }

    public void onBtnTchClick(View view){
        Intent intent = new Intent(this,TeacherActivity.class);
        startActivity(intent);
    }
}