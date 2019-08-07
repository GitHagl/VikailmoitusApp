/*
* Popup to notify of wrong account/password.
*/

package com.example.vikailmoitusapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class LoginPopup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout(width, (int)(height*.15));
    }
}
