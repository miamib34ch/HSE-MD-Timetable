package org.hse.timetableforhsefe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.hse.timetableforhsefe.Group;
import org.hse.timetableforhsefe.api.TimeResponse;
import org.hse.timetableforhsefe.schedule.ScheduleActivity;
import org.hse.timetableforhsefe.schedule.ScheduleMode;
import org.hse.timetableforhsefe.schedule.ScheduleType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public  abstract class BaseActivity extends AppCompatActivity {

    private final static String TAG = "BaseActivity";

    protected TextView time, status, subject, cabinet, corp, teacher;
    protected Date currentTime;
    protected Spinner spinner;
    protected Button btnDay;
    protected Button btnWeek;

   public void get(Date date){
       currentTime = date;
   }

    //присваиваем в переданный текст вью текст
    protected void showTime() {
        if (currentTime != null){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm, EEEE", Locale.forLanguageTag("RU"));
        String[] dateFormatSplit = simpleDateFormat.format(currentTime).split(" ");
        String timeText = "Сейчас: "+dateFormatSplit[0]+" "+dateFormatSplit[1].substring(0,1).toUpperCase()+dateFormatSplit[1].substring(1);
        time.setText(timeText);
        }
        else{
            //если ответ получилось распарсить, но времени там не оказалось
            currentTime = new Date();
            showTime();
        }
    }

    //region Нажатие кнопок расписания

    private Group getSelectedGroup(){
        return (Group)spinner.getSelectedItem();
    }

    protected void onClick(ScheduleType type, ScheduleMode mode, Date currentTime){
        Group group = getSelectedGroup();

        Intent intent = new Intent(this, ScheduleActivity.class);
        intent.putExtra(ScheduleActivity.ARG_MODE, mode);
        intent.putExtra(ScheduleActivity.ARG_TYPE, type);
        intent.putExtra(ScheduleActivity.ARG_NAME, group.getName());
        intent.putExtra(ScheduleActivity.ARG_ID, group.getId());
        intent.putExtra(ScheduleActivity.ARG_DATE, currentTime);
        startActivity(intent);
    }

    //endregion
}


