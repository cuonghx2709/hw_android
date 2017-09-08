package com.example.cuonghx2709.storyapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by cuonghx2709 on 9/5/2017.
 */

public class StoryAdapter extends ArrayAdapter<StoryModel> {

    private static final String TAG = MainActivity.class.toString();
    private Context context;
    private int resource;
    private List<StoryModel> storyModelList;
    public StoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<StoryModel> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.storyModelList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource,parent, false);

        StoryModel storyModel = storyModelList.get(position);

//        View vBookMark = convertView.findViewById(R.id.v_bookmark);
        TextView tvAuthor = convertView.findViewById(R.id.tv_author);
        TextView tvTille = convertView.findViewById(R.id.tv_title);
        ImageView ivStory = convertView.findViewById(R.id.iv_story);

        String imageString = storyModel.getImage();

        String pureBase64Encoded = imageString.substring(imageString.indexOf(",")  + 1);

        byte[] imageBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        Log.d(TAG, "getView: " + decodedImage);
        ivStory.setImageBitmap(decodedImage);

        tvTille.setText(storyModel.getTitle());
        tvAuthor.setText(storyModel.getAuthor());

        return convertView;
    }
}
