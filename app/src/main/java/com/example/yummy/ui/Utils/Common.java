package com.example.yummy.ui.Utils;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Common {


    public static void setStatusBarColor(Activity activity, int color, boolean makeDark) {

        Window window = activity.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(color);
        if (makeDark) {
            View decor = window.getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


    }
}
