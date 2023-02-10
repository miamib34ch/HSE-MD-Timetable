package org.hse.timetableforhsefe.api;

import android.util.Log;

import com.google.gson.Gson;

import org.hse.timetableforhsefe.BaseActivity;
import org.hse.timetableforhsefe.api.TimeResponse;

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

public class NetworkClient {

    private final String URL = "https://api.ipgeolocation.io/ipgeo?apiKey=b03018f75ed94023a005637878ec0977";
    private final OkHttpClient client = new OkHttpClient();
    private final BaseActivity delegate;

    public NetworkClient(BaseActivity delegate){
        this.delegate = delegate;
    }

    //получаем ответ от сервера
    public void getTime(){
        Request request = new Request.Builder().url(URL).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
                parseResponse(response);
            }

            public void onFailure(Call call, IOException e) {
                Log.e("NetworkClient", "getTime", e);
                delegate.get(new Date());
            }
        });
    }

    //если получили ответ
    private void parseResponse(Response response) {
        Gson gson = new Gson();
        ResponseBody body = response.body();
        try {
            if (body == null) {
                return;
            }
            String string = body.string();
            Log.d("NetworkClient", string);
            TimeResponse timeResponse = gson.fromJson(string, TimeResponse.class);
            String currentTimeVal = timeResponse.getTimeZone().getCurrentTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault ());
            delegate.get(simpleDateFormat.parse(currentTimeVal));
        } catch (Exception e) {
            //если ответ не получилось распарсить
            Log.e("NetworkClient","parseResponse", e);
            delegate.get(new Date());
        }
    }
}
