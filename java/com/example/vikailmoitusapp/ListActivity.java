/*
 * Activity for listing all the existing notes
 * Works as the "main view" after the login screen
 */

package com.example.vikailmoitusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Vector;

public class ListActivity extends Activity {

    DBHelper dbOper;
    ListView lv;
    CustomAdapter custAdapter;
    Vector<VikaNote> DBContent;

    private static final String TAG = "LISTA_ ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listwindow);


        // notification and logout buttons*************************************************************************

        Button notifBut = findViewById(R.id.newNotifBut);
        notifBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NewNotifActivity.class));
            }
        });

        Button logoutBut = findViewById(R.id.logoutBut);
        logoutBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginInfo.getInstance().adminLogin = false;
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        // list notes from DB and store them to ListView***************************************************
        // create adapter and couple ListView and data
        // clicking a note will open a view for note details

        dbOper = new DBHelper(this);
        DBContent = dbOper.noteCrudList();
        custAdapter = new CustomAdapter(this, DBContent);

        lv = findViewById(R.id.mylistview);
        lv.setAdapter(custAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int noteID = DBContent.get(i).id;

                Bundle viewBundle = new Bundle();
                viewBundle.putInt("id", noteID);
                Intent in = new Intent(getApplicationContext(),ViewNotifActivity.class);
                in.putExtras(viewBundle);
                startActivity(in);
            }
        });

    }

}
