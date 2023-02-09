package org.hse.timetableforhsefe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.hse.timetableforhsefe.api.NetworkClient;
import org.hse.timetableforhsefe.schedule.ScheduleMode;
import org.hse.timetableforhsefe.schedule.ScheduleType;

import java.util.Date;

public class TeacherActivity extends BaseActivity {

    Group[] mock = {
            new Group(1,"Радионова"),
            new Group(2,"Яборов"),
            new Group(3,"Лядова"),
            new Group(4,"Суворов"),
            new Group(5,"Ветров")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacher);
        spinner = findViewById(R.id.groupList);
        time = findViewById(R.id.time);
        status = findViewById(R.id.status);
        subject = findViewById(R.id.subject);
        cabinet = findViewById(R.id.cabinet);
        corp = findViewById(R.id.corp);
        teacher = findViewById(R.id.teacher);
        btnDay = findViewById(R.id.button_day);
        btnWeek = findViewById(R.id.button_week);

        ArrayAdapter<?> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mock);
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

        NetworkClient networkClient = new NetworkClient(this);
        networkClient.getTime();
        initData();

        //действия кнопок
        btnDay.setOnClickListener(v -> onClick(ScheduleType.DAY, ScheduleMode.TEACHER, currentTime));
        btnWeek.setOnClickListener(v -> onClick(ScheduleType.WEEK, ScheduleMode.TEACHER, currentTime));
    }

    @Override
    public void get(Date date) {
        super.get(date);
        showTime();
    }

    private void initData(){
        status.setText("Нет пар");

        subject.setText("Дисциплина");
        cabinet.setText("Кабинет");
        corp.setText("Корпус");
        teacher.setText("Преподавтель");
    }
}