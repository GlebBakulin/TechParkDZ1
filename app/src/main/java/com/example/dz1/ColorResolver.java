package com.example.dz1;

import android.content.Context;
import android.widget.TextView;

public class ColorResolver {
    static void setColorAndNum(TextView textView, int num, Context context) {
        if (num % 2 == 0) {
            textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.colorBlue));
        }
        textView.setText(Integer.toString(num));
    }
}
