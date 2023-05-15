package edu.ewubd.a2019_3_60_050_assignment_4;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContreactList extends AppCompatActivity {

    private Button btnExit, btnAddNew, btnDeleteAll;
    private ListView eventList;
    List<String> list = new ArrayList<>();
    List<String> eValue = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contreact_list);

        eventList = findViewById(R.id.eventList);
        btnAddNew = findViewById(R.id.btnAddNew);
        btnExit = findViewById(R.id.btnExit);



        String[] keys = {"action", "id", "semester"};
        String[] values = {"restore", "2019360050", "2023-4"};
        httpRequest(keys, values);




        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String key = (String) eventList.getItemAtPosition(pos);
                String value = eValue.get(pos);

               // System.out.println(" Sifat Want to view " + key);
                Intent i = new Intent(ContreactList.this, MainActivity.class);
                i.putExtra("ADDRESS_KEY", key);
                i.putExtra("ADDRESS_VALUES",value);
                startActivity(i);
                // finish();
            }
        });








        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContreactList.this, MainActivity.class);
                startActivity(i);
                // finish();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


    }

    private void httpRequest(final String keys[],final String values[]){
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... voids) {
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                for (int i=0; i<keys.length; i++){
                    params.add(new BasicNameValuePair(keys[i],values[i]));
                }
                String url= "https://muthosoft.com/univ/cse489/index.php";
                String data="";
                try {
                    data=JSONParser.getInstance().makeHttpRequest(url,"POST",params);
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(String data){
                if(data!=null){
                    System.out.println(data);
                    updateEventListByServerData(data);
                    Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
    public void updateEventListByServerData(String data){
        try {
            JSONObject jo = new JSONObject(data);
            if (jo.has("events")) {
                list.clear();
                JSONArray ja = jo.getJSONArray("events");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject event = ja.getJSONObject(i);
                    String eventKey = event.getString("e_key");
                    String eventValue = event.getString("e_value");
                    // split eventValue to show in event list
                    list.add(eventKey);
                    eValue.add(eventValue);
                }
                   ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                eventList.setAdapter(arrayAdapter);

                }
            }

        catch(Exception e){

        }
    }
}