/*
 * VikailmoitusApp
 *
 * A notification tool for the use of housing cooperatives
 *
 * In purpose of notifying the janitor about problems and faults considering the housing cooperative.
 *
 * Normal user can browse and view existing notes and create new notes.
 * Janitor user (admin) can also mark existing notes as checked or done, and delete notes.
 *
 * A mock-up login is implemented with local account database and simple account management. (pending error handling)
 * The login information is held in LoginInfo.
 * The databases are currently local and accessible only from the same device.
 * Account and session management and cloud databases will be added in later versions.
 *
 * The app implements SQLite database.
 *
 * MainActivity serves as the login screen
 *
 */

package com.example.vikailmoitusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /* hardcoded accounts and passwords / variables for SQLite account creation
    final String accAdmin = "admin";
    final String accUser = "Seppo";

    String passAdmin = "admin";
    String passUser = "1234";
    */

    LoginDBHelper loginDB;

    private static final String TAG = "MAIN_ ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDB = new LoginDBHelper(this);

        // Account creation
        // RUN ONLY ONCE TO CREATE THE ACCOUNTS
        //loginDB.loginCrudCreate(accAdmin, passAdmin, 1);
        //loginDB.loginCrudCreate(accUser, passUser, 0);


        Button loginBut = findViewById(R.id.loginButton);
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText textfieldAcc = findViewById(R.id.nameField);
                EditText textfieldPass = findViewById(R.id.passwordField);

                String[] compAcc = new String[1];
                compAcc[0] = textfieldAcc.getText().toString();
                String compPass = textfieldPass.getText().toString();

                // compare given account/password to hardcoded ones (one user and one janitor account given)
                // if login is correct, update LoginInfo and continue to listview
                // if login is incorrect, a popup will notify about this

                String[] localDBinfo = loginDB.loginCrudRead(compAcc);
                String dbAcc = localDBinfo[0];
                String dbPass = localDBinfo[1];
                String dbIsAdmin = localDBinfo[2];

                if (compAcc[0].equals(dbAcc)) {
                    Log.i(TAG, "compAcc equ dbAcc");
                    if (compPass.equals(dbPass)) {
                        Log.i(TAG, "compPass equ dbPass");
                        Log.i(TAG, dbIsAdmin);
                        if (Integer.parseInt(dbIsAdmin) == 1) {
                            Log.i(TAG, "admin login success");

                            LoginInfo.getInstance().adminLogin = true;
                            startActivity(new Intent(getApplicationContext(),ListActivity.class));
                        }else{
                            Log.i(TAG, "user login success");
                            LoginInfo.getInstance().adminLogin = false;
                            startActivity(new Intent(getApplicationContext(),ListActivity.class));
                        }

                    }else{
                        Log.i(TAG, "compPass NOT dbPass");
                        startActivity(new Intent(MainActivity.this, LoginPopup.class));
                    }

                }else{
                    Log.i(TAG, "compAcc NOT dbAcc");
                    startActivity(new Intent(MainActivity.this, LoginPopup.class));
                }
            }
        });
    }
}


//TODO LIST
/*
 * Hardkoodaukset stringeille

 *
 *
 *
 *
 */