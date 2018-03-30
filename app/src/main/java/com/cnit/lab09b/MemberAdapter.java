package com.cnit.lab09b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter {
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Member> data = null;
    private LayoutInflater inflater = null;
    public MemberAdapter(Context c, int l, ArrayList<Member> d) {
        this.mContext = c;
        this.layout = l;
        this.data = d;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return data.size();
    }
    @Override
    public Object getItem(int position) {
// TODO Auto-generated method stub
        return data.get(position).getId();
    }
    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_hobbies = (TextView) convertView.findViewById(R.id.tv_hobbies);
        TextView tv_no = (TextView) convertView.findViewById(R.id.tv_no);
        TextView tv_id = (TextView) convertView.findViewById(R.id.tv_id);
        tv_name.setText("Name : " + data.get(position).getName());
        tv_hobbies.setText("Hobby : " + data.get(position).gethobbies().toString());
        tv_no.setText("Number : " + String.valueOf(data.get(position).getNo()));
        tv_id.setText("ID : " + data.get(position).getId());
        if((position%2) == 1) {
            convertView.setBackgroundColor(0x50efefef);
        } else {
            convertView.setBackgroundColor(0x20b9b9b9);
        }
        return convertView;
    }
}
