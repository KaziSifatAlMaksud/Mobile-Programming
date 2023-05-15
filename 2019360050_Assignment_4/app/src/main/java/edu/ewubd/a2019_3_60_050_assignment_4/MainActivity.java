package edu.ewubd.a2019_3_60_050_assignment_4;


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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText name,mail,phone,phone1, Address;
    Button cancel,save, cont, delete;
    String key="";
    private static final int PICK_IMAGE = 10000;
    private ImageView imageView;
    private Uri imageUri;
    String existingKey = "";
    String existingValue = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name1);
        Address = findViewById(R.id.Adress);
        mail = findViewById(R.id.Mail);
        phone = findViewById(R.id.phone);
        phone1 = findViewById(R.id.phone1);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        cont = findViewById(R.id.Cont);
        delete = findViewById(R.id.Delete);
        delete.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.imageVie);
        save.setOnClickListener(view -> funcSave());
        cancel.setOnClickListener(view -> exit(view));

        Intent i = getIntent();


            if(i.hasExtra("ADDRESS_KEY")) {
            existingKey = i.getStringExtra("ADDRESS_KEY");
            existingValue = i.getStringExtra("ADDRESS_VALUES");
            //Toast.makeText(getApplicationContext(),existingValue,Toast.LENGTH_SHORT).show();


             //   updateEventListByServerData(existingKey);
//            //KeyValueDB db = new KeyValueDB(MainActivity.this);
           // String value = db.getValueByKey(existingKey);
            String[] values = existingValue.split("---");

            name.setText(values[0]);
            Address.setText(values[1]);
            mail.setText(values[2]);
            phone.setText(values[3]);
            phone1.setText(values[4]);
            byte[] decodedString = Base64.decode(values[5], Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
           // db.close();
           save.setText("Update");
            delete.setVisibility(View.VISIBLE);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    funcSave();
                }
            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   KeyValueDB db = new KeyValueDB(MainActivity.this);
                  //  db.deleteDataByKey(existingKey);
                    Intent i = new Intent(MainActivity.this, ContreactList.class);
                    startActivity(i);
                    finish();
                }
            });


       }



        Button btnChoose = findViewById(R.id.btnChoose);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(MainActivity.this, ContreactList.class);
                startActivity(i);
                finish();
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
        String age1 = Address.getText().toString().trim();
        String mail1 = mail.getText().toString().trim();
        String phone2 = phone.getText().toString().trim();
        String phone3 = phone1.getText().toString().trim();


        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);

            // Get the original size of the image
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;

            // Calculate the scaling factor to reduce the image size
            int maxDimension = 500; // Change this value to adjust the maximum size of the image
            float scaleFactor = Math.min((float) maxDimension / imageWidth, (float) maxDimension / imageHeight);

            // Decode a scaled-down version of the image
            inputStream.close();
            inputStream = getContentResolver().openInputStream(imageUri);
            options.inJustDecodeBounds = false;
            options.inSampleSize = Math.round(1 / scaleFactor);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);

            // Compress the image to a maximum of 100 bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            while (outputStream.size() > 100 && quality > 0) {
                quality -= 10;
                outputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            }

            // Encode the compressed image to a Base64 string
            byte[] imageBytes = outputStream.toByteArray();
            String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            System.out.println(imageString);
            if (isValid(mail1)) {


                if (isValidMobileNo(phone2)) {
                    if (isValidMobileNo(phone3)) {
                        if(key.length()==0){
                            key = name1+ System.currentTimeMillis();
                            System.out.println(key);}

                        String value = name1 + "---" + age1 + "---"+ mail1 + "---" + phone2 + "---" + phone3 + "---" + imageString;

                        String[] keys = {"action", "id", "semester", "key", "event"};
                        String[] values = {"backup", "2019360050", "2023-4",key, value};
                        httpRequest(keys, values);

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
                  //  updateEventListByServerData(data);
                    Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
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