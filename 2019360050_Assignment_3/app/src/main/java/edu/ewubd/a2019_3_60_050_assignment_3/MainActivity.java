package edu.ewubd.a2019_3_60_050_assignment_3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText name,mail,phone,phone1,age;
    Button cancel,save;
    String key="";
    private static final int PICK_IMAGE = 10000;
    private ImageView imageView;
    private Uri imageUri;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name1);
        age = findViewById(R.id.Age);
        mail = findViewById(R.id.Mail);
        phone = findViewById(R.id.phone);
        phone1 = findViewById(R.id.phone1);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        save.setOnClickListener(view -> funcSave());
        cancel.setOnClickListener(view -> exit(view));


        Button btnChoose = findViewById(R.id.btnChoose);
        imageView = findViewById(R.id.imageView);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });




    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    public void funcSave(){


        String name1 = name.getText().toString().trim();
        String age1 = age.getText().toString().trim();
        String mail1 = mail.getText().toString().trim();
        String phone2 = phone.getText().toString().trim();
        String phone3 = phone1.getText().toString().trim();



        System.out.println(name1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sifat1);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        try {
            if (isValid(mail1)) {


                if (isValidMobileNo(phone2)) {
                    if (isValidMobileNo(phone3)) {
                        if(key.length()==0){
                            key = name1+ System.currentTimeMillis();
                            System.out.println(key);}
                        String value = name1 + "---" + age1 + "---"+ mail1 + "---" + phone2 + "---" + phone3 + "---" + imageString;
                        KeyValueDB kvdb = new KeyValueDB(this);
                        kvdb.insertKeyValue(key, value);
                        Toast.makeText(getApplicationContext(), "Data Stored!", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid phone number!",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid phone number!",Toast.LENGTH_SHORT).show();

                }
            }
            else{

                Toast.makeText(getApplicationContext(),"Invalid mail!",Toast.LENGTH_SHORT).show();
            }
        }

  

        catch(Exception e){

            Toast.makeText(getApplicationContext(),"Error   Occur!",Toast.LENGTH_SHORT).show();
        }

    }



    private void httpRequest(final String keys[],final String values[]){
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... voids) {
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                for (int i=0; i<keys.length; i++){
                    params.add(new BasicNameValuePair(keys[i],values[i]));
                }
                String url= "https://www.muthosoft.com/univ/cse489/index.php";
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
        try{
            JSONObject jo = new JSONObject(data);
            if(jo.has("events")){
                JSONArray ja = jo.getJSONArray("events");
                for(int i=0; i<ja.length(); i++){
                    JSONObject event = ja.getJSONObject(i);
                    String eventKey = event.getString("e_key");
                    String eventValue = event.getString("e_value");
// split eventValue to show in event list

                }
            }
        }catch(Exception e){}
    }



    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidMobileNo(String str)
    {

        return str.matches("^(?:\\+88|88)?(01[3-9]\\d{8})$");



    }
    public void exit(View view){

        finish();
        System.exit(0);
    }

}