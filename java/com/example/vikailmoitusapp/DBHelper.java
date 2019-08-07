/*
 * SQLite Database handler for notifications
 */

package com.example.vikailmoitusapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.util.Vector;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "mydb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, description TEXT NOT NULL, status INTEGER NOT NULL, timecreated TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    //
    // CRUD Operations
    //

    // CREATE  *********************************************************************

    public void noteCrudCreate(int status, String title, String description, String timecreated) {

        SQLiteDatabase localDB = this.getWritableDatabase();
        ContentValues localContent = new ContentValues();
        localContent.put("status",status);
        localContent.put("title",title);
        localContent.put("description",description);
        localContent.put("timecreated",timecreated);
        localDB.insert("notes", null, localContent);

        localDB.close();
    }

    // READ  ***********************************************************************

    public VikaNote noteCrudRead(int id) {

        SQLiteDatabase localDB = this.getReadableDatabase();
        String[] strID = {Integer.toString(id)};
        Cursor localResult = localDB.rawQuery("SELECT * FROM notes WHERE id = ?", strID);
        localResult.moveToFirst();
        VikaNote localNote = new VikaNote();

        while(localResult.isAfterLast() == false) {
            localNote.id = Integer.parseInt(localResult.getString(localResult.getColumnIndex("id")));
            localNote.status = Integer.parseInt(localResult.getString(localResult.getColumnIndex("status")));
            localNote.title = localResult.getString(localResult.getColumnIndex("title"));
            localNote.description = localResult.getString(localResult.getColumnIndex("description"));
            localNote.timecreated = localResult.getString(localResult.getColumnIndex("timecreated"));
            localResult.moveToNext();
        }

        localDB.close();
        return localNote;
    }

    // UPDATE  *********************************************************************

    public void noteCrudUpdate(int id, int status, String title, String description, String timecreated) {

        SQLiteDatabase localDB = this.getWritableDatabase();
        String[] strID = {Integer.toString(id)};
        ContentValues localContent = new ContentValues();
        localContent.put("status",status);
        localContent.put("title",title);
        localContent.put("description",description);
        localContent.put("timecreated",timecreated);
        localDB.update("notes", localContent, "id = ?", strID);

        localDB.close();
    }

    // DELETE  *********************************************************************

    public void noteCrudDelete(int id) {

        SQLiteDatabase localDB = this.getWritableDatabase();
        String[] strID = {Integer.toString(id)};
        localDB.delete("notes", "id = ?", strID);

        localDB.close();
    }

    // LIST  ***********************************************************************

    public Vector<VikaNote> noteCrudList() {

        Vector<VikaNote> localList = new Vector<>();
        SQLiteDatabase localDB = this.getReadableDatabase();
        Cursor localResult = localDB.rawQuery("SELECT * FROM notes", null);
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
}
