package com.example.cuonghx2709.hw_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuonghx2709.hw_android.info.Main;
import com.example.cuonghx2709.hw_android.info.MainObjectJSON;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String KEY = "9c3ab78c411781247eb0b124611b79a8" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.edt_city);
        Button button = (Button) findViewById(R.id.bt_check);
        final TextView tvCity = (TextView) findViewById(R.id.tv_city);
        final TextView tvTemp = (TextView) findViewById(R.id.tv_temp);
        final TextView tvMain = (TextView) findViewById(R.id.tv_sunrise);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.openweathermap.org/" )
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetObjectService getObjectService = retrofit.create(GetObjectService.class);
                if (s != null) {
                    getObjectService.getObject(s, KEY).enqueue(new Callback<MainObjectJSON>() {
                        @Override
                        public void onResponse(Call<MainObjectJSON> call, Response<MainObjectJSON> response) {
                            if (response.body() != null){
                                tvCity.setText("City: " + response.body().getName());
                                tvTemp.setText(String.format("temp : %.2f C", response.body().getMain().getTemp() - 273.15));
                                tvMain.setText("main: " + response.body().getWeather().get(0).getMain());
                            }else {
                                Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<MainObjectJSON> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "No Connection", Toast.LENGTH_SHORT).show();
                            Log.d("1312313", "onFailure: " + t.toString());
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this, "Select your City", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
