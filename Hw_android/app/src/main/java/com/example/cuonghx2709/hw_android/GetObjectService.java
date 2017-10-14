package com.example.cuonghx2709.hw_android;

import android.support.v4.media.MediaBrowserCompat;

import com.example.cuonghx2709.hw_android.info.MainObjectJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Query;

/**
 * Created by cuonghx2709 on 10/13/2017.
 */

public interface GetObjectService {

    @GET("data/2.5/weather")
    Call<MainObjectJSON> getObject(@Query("q") String name, @Query("APPID") String string);
}
