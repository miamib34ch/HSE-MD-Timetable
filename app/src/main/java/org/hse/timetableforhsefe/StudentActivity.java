package org.hse.timetableforhsefe;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.hse.timetableforhsefe.api.NetworkClient;
import org.hse.timetableforhsefe.schedule.ScheduleMode;
import org.hse.timetableforhsefe.schedule.ScheduleType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentActivity extends BaseActivity {

    //region Создание мок данных

    String[] course = {"ПИ","БИ"};
    int[] year = {19,20};
    int[] groupNumber = {1,2};
    List<Group> mock = new ArrayList<>();

    void enumeration(String[] course, int[] year, int[] groupNumber){
        int count = 1;
        for(String s: course){
            for(int i: year){
                for(int j: groupNumber){
                    mock.add(new Group(count,assembly(s,i,j)));
                    count++;
                }
            }
        }
    }

    String assembly(String course, int year, int groupNumber){
        return course + "-" + year + "-" + groupNumber;
    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student);
        spinner = findViewById(R.id.groupList);
        time = findViewById(R.id.time);
        status = findViewById(R.id.status);
        subject = findViewById(R.id.subject);
        cabinet = findViewById(R.id.cabinet);
        corp = findViewById(R.id.corp);
        teacher = findViewById(R.id.teacher);
        btnDay = findViewById(R.id.button_day);
        btnWeek = findViewById(R.id.button_week);

        //создаём моки
        enumeration(course, year, groupNumber);

        //добавляем моки
        ArrayAdapter<?> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mock);
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

        //устанавливаем данные
        NetworkClient networkClient = new NetworkClient(this);
        networkClient.getTime();
        initData();

        //действия кнопок
        btnDay.setOnClickListener(v -> onClick(ScheduleType.DAY, ScheduleMode.STUDENT,currentTime));
        btnWeek.setOnClickListener(v -> onClick(ScheduleType.WEEK, ScheduleMode.STUDENT, currentTime));
    }

    @Override
    public void get(Date date) {
        super.get(date);
        showTime();
    }

    //какая сейчас пара
    private void initData(){
        status.setText("Нет пар");
        subject.setText("Дисциплина");
        cabinet.setText("Кабинет");
        corp.setText("Корпус");
        teacher.setText("Преподаватель");
    }
}

