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

    String[] course = {"ПИ","БИ"};
    int[] year = {19,20};
    int[] groupNumber = {1,2};
    List<StudentActivity.Group> mock = new ArrayList<>();

    void enumeration(String[] course, int[] year, int[] groupNumber){
        int count = 1;
        for(String s: course){
            for(int i: year){
                for(int j: groupNumber){
                    mock.add(new StudentActivity.Group(count,assembly(s,i,j)));
                    count++;
                }
            }
        }
    }

    String assembly(String course, int year, int groupNumber){
        return course + "-" + year + "-" + groupNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        enumeration(course, year, groupNumber);
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

    private void initGroupList(List<StudentActivity.Group> groups, List<StudentActivity.Group> list){
        for (StudentActivity.Group group: list){
            groups.add(new StudentActivity.Group(group.getId(),group.getName()));
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

