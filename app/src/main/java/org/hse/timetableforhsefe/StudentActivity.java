package org.hse.timetableforhsefe;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StudentActivity extends AppCompatActivity {
    private TextView time, status, subject, cabinet, corp, teacher;
    Date currentTime;
    StudentActivity.Group[] mock = {
            new StudentActivity.Group(1,"ПИ-20-1"),
            new StudentActivity.Group(2,"ПИ-20-2"),
            new StudentActivity.Group(3,"ПИ-19-1"),
            new StudentActivity.Group(4,"ПИ-19-2"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        final Spinner spinner = findViewById(R.id.groupList);

        List<Group> groups = new ArrayList();
        initGroupList(groups, mock);

        ArrayAdapter<?> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedID) {
                Object item = adapter.getItem(selectedItemPosition);
                Log.d(TAG, "selectedItem: " + item);
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
        for (StudentActivity.Group group: list){
            groups.add(new StudentActivity.Group(group.getId(),group.getName()));
        }
    }

    private void initTime(){
        currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm, EEEE", Locale.forLanguageTag("RU"));
        time.setText(simpleDateFormat.format(currentTime));
    }

    private void initData(){
        status.setText("Нет пар");

        subject.setText("Дисциплина");
        cabinet.setText("Кабинет");
        corp.setText("Корпус");
        teacher.setText("Преподавтель");
    }

    static class Group {
        private Integer id;
        private String name;

        public Group(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

