package org.hse.timetableforhsefe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnStdClick(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Расписание студента", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onBtnTchClick(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Расписание преподавателя", Toast.LENGTH_SHORT);
        toast.show();
    }
}