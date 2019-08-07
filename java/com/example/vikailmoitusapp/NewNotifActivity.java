/*
 * Activity for creating a new note
 */

package com.example.vikailmoitusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNotifActivity extends Activity {

    private DBHelper newNotifDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnotif);
        newNotifDB = new DBHelper(this);

        Button saveBut = findViewById(R.id.newSaveBut);
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote(view);
            }
        });
    }

    // Take values from the text fields and add a new note to DB with a timestamp
    // Return to the list view

    public void saveNote(View v) {

        EditText localTitle = findViewById(R.id.newNoteTitle);
        EditText localDesc = findViewById(R.id.newNoteDesc);

        String localTitleUpdate = localTitle.getText().toString();
        String localDescUpdate = localDesc.getText().toString();
        String localTime = (DateFormat.format("dd.MM.yyyy hh:mm:ss", new java.util.Date()).toString());

        newNotifDB.noteCrudCreate(0, localTitleUpdate, localDescUpdate, localTime);

        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }
}
