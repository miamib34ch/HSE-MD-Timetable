package org.hse.timetableforhsefe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Locale;

public class TeacherActivity extends AppCompatActivity {

    private TextView time, status, subject, cabinet, corp, teacher;
    Date currentTime;
    StudentActivity.Group[] mock = {
            new StudentActivity.Group(1,"Радионова"),
            new StudentActivity.Group(2,"Яборов"),
            new StudentActivity.Group(3,"Лядова"),
            new StudentActivity.Group(4,"Суворов"),
            new StudentActivity.Group(5,"Ветров")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        final Spinner spinner = findViewById(R.id.groupList);

        List<StudentActivity.Group> groups = new ArrayList<>();
        initGroupList(groups,mock);

        ArrayAdapter<?> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                Object item = adapter.getItem(selectedItemPosition);
                Log.d("TAG", "selectedItem" + item);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        time = findViewById(R.id.time);
        initTime();

        status = findViewById(R.id.status);
        subject = findViewById(R.id.subject);
        cabinet = findViewById(R.id.cabinet);
        corp = findViewById(R.id.corp);
        teacher = findViewById(R.id.teacher);

        initData();
    }

    private void initGroupList(List<StudentActivity.Group> groups, StudentActivity.Group[] list){
        for (StudentActivity.Group teacher: list){
            groups.add(new StudentActivity.Group(teacher.getId(),teacher.getName()));
        }
    }

    private void initTime(){
        currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm, EEEE", Locale.forLanguageTag("RU"));
        String[] dateFormatSplit = simpleDateFormat.format(currentTime).split(" ");
        String timeText = "Сегодня: "+dateFormatSplit[0]+" "+dateFormatSplit[1].substring(0,1).toUpperCase()+dateFormatSplit[1].substring(1);
        time.setText(timeText);
    }

    private void initData(){
        status.setText("Нет пар");

        subject.setText("Дисциплина");
        cabinet.setText("Кабинет");
        corp.setText("Корпус");
        teacher.setText("Преподавтель");
    }
}