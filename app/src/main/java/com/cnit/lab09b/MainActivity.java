package com.cnit.lab09b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    String urlAddr = "http://web.ics.purdue.edu/~minb/json_students";
    Button btnNetCon;
    ListView listView;
    ArrayList<Member> members;
    MemberAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNetCon = (Button) findViewById(R.id.bt_network_con);
        btnNetCon.setOnClickListener(listener);
        listView = (ListView) findViewById(R.id.lv_members);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_network_con:
                    try {
                        NetworkAsyncTask netWorkAsyncTask = new NetworkAsyncTask(MainActivity.this, urlAddr);
                        Object obj = netWorkAsyncTask.execute().get();
                        members = (ArrayList<Member>) obj;
                        Log.i(TAG, "members.size() : " + members.size());
                        adapter = new MemberAdapter(MainActivity.this, R.layout.custom_layout, members);
                        listView.setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
}
