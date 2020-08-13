package com.fahim.android_client.app;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataBindingAdapter {

    @BindingAdapter("android:text")
    public static void showDate(TextView textView, Date date) {
        if (date != null && textView != null) {
            DateFormat dateFormat = new SimpleDateFormat("hh-mm-ss aa, dd/MMM/yyyy", Locale.ENGLISH);
            textView.setText(String.format("Time: %s", dateFormat.format(date)));
        }
    }
}