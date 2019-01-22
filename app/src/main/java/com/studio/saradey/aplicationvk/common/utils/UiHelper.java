package com.studio.saradey.aplicationvk.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.studio.saradey.aplicationvk.R;

/**
 * @author jtgn on 14.08.2018
 */


// методом, который будет скрывать текстовое поле, если в записи нет текста.
// Этот метод мы будем использовать также и при открытии комментариев в дальнейшем.
public class UiHelper {
    private static UiHelper ourInstance = new UiHelper();

    private Resources resources;
    private Context context;

    public static UiHelper getInstance() {
        return ourInstance;
    }


    public void setUpTextViewWithVisibility(TextView textView, String s) {
        textView.setText(s);

        if (s.length() != 0) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    //который добавляет строку «поделился» для репостов
    public void setUpTextViewWithMessage(TextView textView, String s, String messageIfEmpty) {
        String s1;
        int color;
        Resources res = textView.getResources();

        if (s.length() != 0) {
            textView.setVisibility(View.VISIBLE);
            color = android.R.color.primary_text_light;

            s1 = s;

        } else {
            s1 = "Поделился";
            color = R.color.colorIcon;
        }

        textView.setText(s1);
        textView.setTextColor(res.getColor(color));
    }

}
