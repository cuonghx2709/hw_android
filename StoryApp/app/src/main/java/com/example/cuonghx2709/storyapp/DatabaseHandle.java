package com.example.cuonghx2709.storyapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by cuonghx2709 on 9/5/2017.
 */

public class DatabaseHandle {

    private MyDatabase myDatabase;
    private static DatabaseHandle databaseHandle;



    public DatabaseHandle(Context context){
        myDatabase = new MyDatabase(context);
    }

    public static DatabaseHandle getInstance(Context context){
        if (databaseHandle == null){
            databaseHandle = new DatabaseHandle(context);
        }
        return  databaseHandle;
    }

    private SQLiteDatabase sqLiteDatabase;


    public List<StoryModel> getListStory(){
        List<StoryModel> storyModelsList = new ArrayList<>();

        sqLiteDatabase = myDatabase.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_short_story", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            //get data
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String title = cursor.getString(2);
            String description = cursor.getString(3);
            String content = cursor.getString(4);
            String author = cursor.getString(5);
            boolean bookmark = cursor.getInt(0) != 0;

            StoryModel storyModel = new StoryModel();
            storyModel.setAuthor(author);
            storyModel.setBookmark(bookmark);
            storyModel.setContent(content);
            storyModel.setDescription(description);
            storyModel.setId(id);
            storyModel.setImage(image);
            storyModel.setTitle(title);
            storyModelsList.add(storyModel);


            //move cursor
            cursor.moveToNext();

        }
        return storyModelsList;
    }
}
