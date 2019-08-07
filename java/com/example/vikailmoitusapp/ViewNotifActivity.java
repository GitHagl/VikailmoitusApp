/*
 * Activity for details for selected note
 */

package com.example.vikailmoitusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewNotifActivity extends Activity {

    private DBHelper viewDB;
    private int noteId = 0;
    private int noteStatus = 0;
    private String noteTime = "";
    private String noteTitle = "";
    private String noteDesc = "";
    private String testi = "";

    boolean isAdmin = false;
    private static final String TAG = "VIEW_ ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotif);
        viewDB = new DBHelper(this);

        // set check, done and delete buttons *********************************************************
        Button checkBut = findViewById(R.id.viewCheckBut);
        Button doneBut = findViewById(R.id.viewDoneBut);
        Button delBut = findViewById(R.id.viewDeleteBut);

        checkBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifMarkCheck();
            }
        });
        doneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifMarkDone();
            }
        });
        delBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifDelete();
            }
        });

        // open Bundle to get the Note to be examined**************************************************
        // fill text fields with respective Note data
        Bundle noteBundle = getIntent().getExtras();

        if (noteBundle != null) {

            if (LoginInfo.getInstance().adminLogin) {
                checkBut.setVisibility(View.VISIBLE);
                doneBut.setVisibility(View.VISIBLE);
                delBut.setVisibility(View.VISIBLE);
            }

            int localID = noteBundle.getInt("id");
            if (localID > 0) {

                VikaNote localIlmo = viewDB.noteCrudRead(localID);

                TextView viewID = findViewById(R.id.idText);
                TextView viewTime = findViewById(R.id.aikaText);
                TextView viewTitle = findViewById(R.id.notifTitleText);
                TextView viewDesc = findViewById(R.id.notifDescriptionText);
                TextView viewStatus = findViewById(R.id.notifStatusText);

                noteId = localIlmo.id;
                testi = Integer.toString(noteId);
                noteStatus = localIlmo.status;

                noteTime = localIlmo.timecreated;
                noteTitle = localIlmo.title;
                noteDesc = localIlmo.description;


                viewID.setText(testi);
                viewTime.setText(noteTime);
                viewTitle.setText(noteTitle);
                viewDesc.setText(noteDesc);

                switch (noteStatus) {
                    case 1:
                        viewStatus.setBackgroundColor(getResources().getColor(R.color.kuitattu));
                        viewStatus.setText("Kuitattu");
                        break;

                    case 2:
                        viewStatus.setBackgroundColor(getResources().getColor(R.color.hoidettu));
                        viewStatus.setText("Hoidettu");
                        break;

                    default:
                        viewStatus.setBackgroundColor(getResources().getColor(R.color.kuittaamaton));
                        viewStatus.setText("Kuittaamaton");


                }

            }


        }

    }

    // functions for check, done and delete buttons****************************************************

    public void notifMarkCheck() {
        viewDB.noteCrudUpdate(noteId, 1, noteTitle, noteDesc, noteTime);
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }

    public void notifMarkDone() {
        viewDB.noteCrudUpdate(noteId, 2, noteTitle, noteDesc, noteTime);
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }

    public void notifDelete() {
        Log.i(TAG, "ALOG VIEW delete");
        viewDB.noteCrudDelete(noteId);
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }

}