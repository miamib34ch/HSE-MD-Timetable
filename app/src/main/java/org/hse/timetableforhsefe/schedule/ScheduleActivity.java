package org.hse.timetableforhsefe.schedule;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.hse.timetableforhsefe.R;
import org.hse.timetableforhsefe.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduleActivity extends BaseActivity {

    public static String ARG_TYPE = "Type";
    public static String ARG_MODE = "Mode";
    public static String ARG_NAME = "Name";
    public static String ARG_ID = "Id";
    public static String ARG_DATE = "Date";

    private ScheduleMode mode;
    private ScheduleType type;
    private String name;
    private int id;
    private Date date;

    private ItemAdapter adapter;
    RecyclerView recyclerView;

    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //получаем данные, которые передавали при создании
        name = getIntent().getStringExtra(ARG_NAME);
        type = (ScheduleType)getIntent().getSerializableExtra(ARG_TYPE);
        mode = (ScheduleMode)getIntent().getSerializableExtra(ARG_MODE);
        id = getIntent().getIntExtra(ARG_ID, 0);
        date = (Date)getIntent().getSerializableExtra(ARG_DATE);

        title = findViewById(R.id.schTitle);
        title.setText(name);

        recyclerView = findViewById(R.id.timetable_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        initData();
    }

    private void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, d MMMM", Locale.forLanguageTag("RU"));
        String[] dateFormatSplit = simpleDateFormat.format(date).split(" ");
        String timeText = dateFormatSplit[0].substring(0,1).toUpperCase()+dateFormatSplit[0].substring(1)+" "+dateFormatSplit[1]+" "+dateFormatSplit[2];


        List<ScheduleItem> list = new ArrayList<> ();

        ScheduleItemHeader header = new ScheduleItemHeader() ;
        header.setDateString(timeText);
        list.add(header);

        ScheduleItem item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem();
        item.setStart("12:00");
        item.setEnd("13:00");
        item.setType("Практическое занятие") ;
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        header = new ScheduleItemHeader() ;
        header.setDateString("Завтра");
        list.add(header);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        item = new ScheduleItem ();
        item.setStart("10:00");
        item.setEnd("11:00");
        item.setType("Практическое занятие");
        item.setName("Анализ данных (анг)");
        item.setPlace("Ауд. 503, Кочновский пр-д, д.3");
        item.setTeacher("Пред. Гущим Михаил Иванович");
        list.add(item);

        adapter.setData(list);
    }
}
