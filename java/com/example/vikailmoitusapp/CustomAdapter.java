/*
* Custom adapter to get status and title from VikaNote and arrange them in a list item layout (status indicated by colour)
* List item defined in list_item.xml
*/

package com.example.vikailmoitusapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.util.Vector;

public class CustomAdapter extends ArrayAdapter<VikaNote> {

    private Context mContext;
    private Vector<VikaNote> vikaNoteList;

    public CustomAdapter(@NonNull Context context, Vector<VikaNote> list) {
        super(context, 0 , list);
        mContext = context;
        vikaNoteList = list;
    }

    @NonNull
    @Override
    public View getView(int position, View contentView, ViewGroup parent) {

        View row = contentView;
        if(row == null){
            row = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        }

        VikaNote curNote = vikaNoteList.get(position);

        TextView statusBox = (TextView) row.findViewById(R.id.textview_status);

        switch (curNote.getStatus()) {
            case 1:
                statusBox.setBackgroundColor(Color.parseColor("#585433"));
                break;

            case 2:
                statusBox.setBackgroundColor(Color.parseColor("#334d33"));
                break;

            default:
                statusBox.setBackgroundColor(Color.parseColor("#404040"));
        }

        TextView titleBox = (TextView) row.findViewById(R.id.textview_title);
        titleBox.setText(curNote.getTitle());

        return row;
    }
}
