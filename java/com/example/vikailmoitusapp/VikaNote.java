/*
* Class for storing note information
*
* status = whether note is unchecked (0), checked (1), or done (2).
*/

package com.example.vikailmoitusapp;

public class VikaNote {
    public int id;
    public int status = 0;
    public String title;
    public String description;
    public String timecreated;

    @Override
    public String toString() {
        return title;
    }

    public int getStatus(){
        return status;
    }

    public String getTitle(){
        return title;
    }
}
