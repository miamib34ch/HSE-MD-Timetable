package org.hse.timetableforhsefe.settings;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import org.hse.timetableforhsefe.BuildConfig;
import org.hse.timetableforhsefe.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light;
    private TextView sensorLight;

    private EditText nameEdit;
    private ImageView userPhoto;
    Uri photoURI;

    private PreferenceManager preferenceManager; //преференс менеджер для хранения кэша

    private static final String TAG = "SettingsActivity"; //тэг для логкэта
    private static final String PERMISSION = "android.permission.CAMERA"; //разрешение которое запрашиваем
    private static final Integer REQUEST_PERMISSION_CODE = 1; //код который вернётся в onRequestPermissionsResult, помогает различать запрос
    private static final Integer REQUEST_IMAGE_CAPTURE = 2; //код который вернётся в onActivityResult, помогает различать запрос

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        preferenceManager = new PreferenceManager(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorLight = findViewById(R.id.lightLevel);
        sensorLight.setText("0 lux");

        nameEdit = findViewById(R.id.name);
        getName();

        TextView allSensors = findViewById(R.id.allSensors);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        String allSensorsText = "";
        for (Sensor sensor : sensors)
            allSensorsText += sensor.getName() + '\n';
        allSensors.setText(allSensorsText);

        allSensors.setMovementMethod(new ScrollingMovementMethod());

        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        Button uploadPhoto = findViewById(R.id.uploadPhoto);
        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        userPhoto = findViewById(R.id.userPhoto);
        getPhoto();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        sensorLight.setText(lux + " lux");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            int permissionCheck = ActivityCompat.checkSelfPermission(this, PERMISSION); //Этот метод возвращает либо PERMISSION_GRANTED, либо PERMISSION_DENIED, в зависимости от того, имеет ли ваше приложение разрешение.
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent(); //если есть разрешение фоткаем
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            userPhoto.setImageURI(photoURI);
        }
    }

    private void getName() {
        String name = preferenceManager.getValue("name", "");
        if (name != "")
            nameEdit.setText(name);
    }

    private void getPhoto() {
        String uri = preferenceManager.getValue("avatar", "");
        if (uri != ""){
            photoURI = Uri.parse(uri);
            userPhoto.setImageURI(photoURI);
        }
    }

    private void save() {
        if (nameEdit.getText() != null)
            preferenceManager.saveValue("name", nameEdit.getText().toString());
        if (photoURI != null)
            preferenceManager.saveValue("avatar", photoURI.toString());
        Toast.makeText(getApplicationContext(), getString(R.string.saved_message), Toast.LENGTH_SHORT).show();
    }

    public void checkPermission() {
        int permissionCheck = ActivityCompat.checkSelfPermission(this, PERMISSION); //Этот метод возвращает либо PERMISSION_GRANTED, либо PERMISSION_DENIED, в зависимости от того, имеет ли ваше приложение разрешение.
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION)){ //если пользователь отметил что не разрешает право то вернёт тру и покажет окно
                Toast.makeText(getApplicationContext(), getString(R.string.ask_photo_permits), Toast.LENGTH_LONG).show();
            }else {
                ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, REQUEST_PERMISSION_CODE);
            }
        } else {
            dispatchTakePictureIntent(); //если есть разрешение фоткаем
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //создаём экран кмеры
        File photoFile = null;
        try {
            photoFile = createImageFile(); // создаём пустой файл фото
        } catch (IOException ex) {
            Log.e(TAG, "Create file", ex);
        }
        if (photoFile != null) {
            photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", photoFile); //сохраняем Uri созданной пустой фотки
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI); //задаём свойство, что сохраняем результат (фото) по указанному юри
            // если пользователь отменит снимок, то пустой файл останется! + старые фотки не удаляются
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "Start activity", e);
            }
        }
    }

    private File createImageFile() throws IOException {
        File pathOfStorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); //путь к стандартному месту хранению картинок, которое доступно пользователю

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePrefix = "img_" + timeStamp + "_";
        String suffix = ".jpg";

        File image = File.createTempFile(filePrefix, suffix, pathOfStorageDir); //создаёт файл (с рандомными цифрами в названии) нужно новое название на случай если пользователь хочет отменить сохранение
        return image;
    }
}

