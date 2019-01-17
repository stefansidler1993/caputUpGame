package com.electronic.lapsus.caputup;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Category> {

    private final Activity context;
    private final List<Category> itemName;

    public CustomListAdapter(Activity context, List<Category> itemName) {
        super(context, R.layout.categorielist, itemName);
        this.context = context;
        this.itemName = itemName;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.categorielist, null,true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.categoryName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.categoryImage);

        final Category category = itemName.get(position);
        txtTitle.setText(StringUtils.capitalize(category.getTitle()));

        URL url = null;
        try {
            url = new URL(category.getImageUrl());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return rowView;
    }
}
