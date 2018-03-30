package com.cnit.lab09b;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkAsyncTask extends AsyncTask<Integer, String, Integer> {
    final static String TAG = "NetworkAsyncTask";
    Context mContext = null;
    String mAddr;
    ProgressDialog dialog = null;
    ArrayList<Member> members;
    public NetworkAsyncTask(Context c, String a) {
        mContext = c;
        mAddr = a;
        members = new ArrayList<Member>();
    }
    @Override
    protected void onPreExecute() {
        Log.i(TAG, "onPreExecute()");
        dialog = new ProgressDialog(mContext);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Dialog");
        dialog.setMessage("down...");
        dialog.show();
    }
    @Override
    protected Integer doInBackground(Integer... params) {
        Log.i(TAG, "doInBackground()");
        StringBuffer sb = new StringBuffer();
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(mAddr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                while (true) {
                    String strLine = br.readLine();
                    if (strLine == null) break;
                    sb.append(strLine + "\n");
                }
                Log.
                        i(TAG, "sb : " + sb.toString());
                parser(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (isr != null) isr.close();
                if (is != null) is.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Log.
                i(TAG, "onProgressUpdate()");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        Log.
                i(TAG, "onCancelled()");
        super.onCancelled();
    }
    private void parser(String s) {
        Log.
                i(TAG, "parser()");
        Log.
                i(TAG, "s : " + s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("members_info"));
            members.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String name = jsonObject1.getString("name");
                int age = jsonObject1.getInt("year");
                Log.i(TAG, "name : " + name);
                ArrayList<String> hobbies = new ArrayList<String>();
                JSONArray jsonArray2 = jsonObject1.getJSONArray("hobbies");
                for (int j = 0; j < jsonArray2.length(); j++) {
                    String hobby = jsonArray2.getString(j);
                    Log.i(TAG, "hobbies[" + j + "] : " + hobby);
                    hobbies.add(hobby);
                }
                JSONObject jsonObject2 = jsonObject1.getJSONObject("info");
                int no = jsonObject2.getInt("no");
                Log.i(TAG, "no : " + no);
                String id = jsonObject2.getString("id");
                Log.i(TAG, "id : " + id);
                int pw = jsonObject2.getInt("pw");
                Log.i(TAG, "pw : " + pw);
                Member member = new Member(name, age, hobbies, no, id, pw);
                members.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
