/*
 * SQLite Database handler for accounts
 */

package com.example.vikailmoitusapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.util.Vector;


public class LoginDBHelper extends SQLiteOpenHelper {

    public LoginDBHelper(Context context) {
        super(context, "accountsdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE accounts (id INTEGER PRIMARY KEY AUTOINCREMENT, account TEXT NOT NULL UNIQUE, password TEXT NOT NULL, isadmin INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        db.execSQL("DROP TABLE IF EXISTS accounts");
        onCreate(db);
    }

    //
    // CRUD Operations
    //

    // CREATE  *********************************************************************

    public void loginCrudCreate(String account, String password, int isadmin) {

        SQLiteDatabase localDB = this.getWritableDatabase();
        ContentValues localContent = new ContentValues();
        localContent.put("account",account);
        localContent.put("password",password);
        localContent.put("isadmin",isadmin);
        localDB.insert("accounts", null, localContent);

        localDB.close();
    }

    // READ  ***********************************************************************

    public String[] loginCrudRead(String[] acc) {

        SQLiteDatabase localDB = this.getReadableDatabase();
        String[] strAcc = acc;
        Cursor localResult = localDB.rawQuery("SELECT * FROM accounts WHERE account = ?", strAcc);
        localResult.moveToFirst();
        String[] accpass = new String[3];

        while(localResult.isAfterLast() == false) {
            accpass[0] = localResult.getString(localResult.getColumnIndex("account"));
            accpass[1] = localResult.getString(localResult.getColumnIndex("password"));
            accpass[2] = Integer.toString(localResult.getInt(localResult.getColumnIndex("isadmin")));
            localResult.moveToNext();
        }

        localDB.close();
        return accpass;
    }

    // UPDATE  *********************************************************************

    public void loginCrudUpdate(int id, String account, String password) {

        SQLiteDatabase localDB = this.getWritableDatabase();
        String[] strID = {Integer.toString(id)};
        ContentValues localContent = new ContentValues();
        localContent.put("account",account);
        localContent.put("password",password);
        localDB.update("notes", localContent, "id = ?", strID);

        localDB.close();
    }

    // DELETE  *********************************************************************

    public void loginCrudDelete(int id) {

        SQLiteDatabase localDB = this.getWritableDatabase();
        String[] strID = {Integer.toString(id)};
        localDB.delete("notes", "id = ?", strID);

        localDB.close();
    }

    // LIST  ***********************************************************************

    /*
    public Vector<VikaNote> loginCrudList() {

        Vector<VikaNote> localList = new Vector<>();
        SQLiteDatabase localDB = this.getReadableDatabase();
        Cursor localResult = localDB.rawQuery("SELECT * FROM accounts", null);
        localResult.moveToFirst();

        while(localResult.isAfterLast() == false) {
            VikaNote localNote = new VikaNote();
            localNote.id = Integer.parseInt(localResult.getString(localResult.getColumnIndex("id")));
            localNote.status = Integer.parseInt(localResult.getString(localResult.getColumnIndex("status")));
            localNote.title = localResult.getString(localResult.getColumnIndex("title"));
            localNote.description = localResult.getString(localResult.getColumnIndex("description"));
            localNote.timecreated = localResult.getString(localResult.getColumnIndex("timecreated"));
            localList.add(localNote);
            localResult.moveToNext();
        }

        localDB.close();
        return localList;
    }
    */
}
