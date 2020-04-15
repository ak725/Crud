package com.example.crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListAdapter extends BaseAdapter {

    private ArrayList<UserData> userData;
    private Context context;

    ContactListAdapter(Context context, ArrayList<UserData> userData){
        this.context = context;
        this.userData = userData;
    }

    @Override
    public int getCount() {
        return userData.size();
    }

    @Override
    public Object getItem(int position) {
        return userData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userData.get(position).uid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_3,parent,false);
        }
        UserData currentUserData = (UserData) getItem(position);

        TextView Name = convertView.findViewById(R.id.listview_item_contact_name);
        TextView Number = convertView.findViewById(R.id.listview_item_contact_number);
        Name.setText(currentUserData.firstName);
        Number.setText(currentUserData.phone_number);
        return convertView;
    }

    void ChangeDataSet(ArrayList<UserData> userData){
        this.userData = userData;
        notifyDataSetChanged();
    }


}