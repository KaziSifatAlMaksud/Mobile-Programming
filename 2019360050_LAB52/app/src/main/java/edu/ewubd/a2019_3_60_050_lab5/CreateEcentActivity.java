package edu.ewubd.a2019_3_60_050_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEcentActivity extends AppCompatActivity {

    private EditText eName, ePlace, eDateTime,eCapacity,eBudget,eEmail,ePhone,eDescription;
    private TextView error1;
    private RadioButton rdOnline;
    private Button btnSave, btnCancel, btnShare ,btnExit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ecent);

        Button btnExit = findViewById(R.id.btnExit);
        EditText eName = findViewById(R.id.etName);
        EditText ePlace = findViewById(R.id.etPlace);
        RadioButton rdOnline = findViewById(R.id.rdOnline);
        EditText eDateTime = findViewById(R.id.etDateTime);
        EditText eCapacity = findViewById(R.id.etCapacity);
        EditText eBudget = findViewById(R.id.etBudget);
        EditText eEmail = findViewById(R.id.etEmail);
        EditText ePhone = findViewById(R.id.etPhone);
        EditText eDescription = findViewById(R.id.etDescription);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnShare = findViewById(R.id.btnShare);
        TextView error1 =findViewById(R.id.error1);

        SharedPreferences sp1 = getSharedPreferences("useraccount",MODE_PRIVATE);
        SharedPreferences.Editor sp1editor = sp1.edit();
        String userexist = sp1.getString("userid","").toString();
        boolean isrememberedLogin = sp1.getBoolean("isremLogin",false);
        boolean isrememberedId = sp1.getBoolean("isremUserid",false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp1editor.putString("userid", "");
                sp1editor.putBoolean("isremLogin", false);
                sp1editor.putBoolean("isremUserid",false);
                sp1editor.apply();
                Intent intent = new Intent( view.getContext() , MainActivity.class);
                startActivity(intent);

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eName.getText().toString().trim();
                String place = ePlace.getText().toString().trim();
                boolean isOnlineSelected = rdOnline.isChecked();
                String time = eDateTime.getText().toString().trim();
                String capacity = eCapacity.getText().toString().trim();
                String budget = eBudget.getText().toString().trim();
                String email = eEmail.getText().toString().trim();
                String phone = ePhone.getText().toString().trim();
                String description = eDescription.getText().toString().trim();
                String value = "Name: "+ name + "\n" + "Place: " + place + "\n"+ "Type: " +isOnlineSelected + "\n" + "Time: " + time+  "/n"+ "Capcity :" + capacity +"\n" + "Budget: " + budget +"\n"+ "Email: " + email + "\n"+ "Phone: " + phone+"\n"+"Description: " +description;


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, value);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String error = "";
                String name = eName.getText().toString().trim();
                String place = ePlace.getText().toString().trim();
                boolean isOnlineSelected = rdOnline.isChecked();
                String time = eDateTime.getText().toString().trim();
                String capacity = eCapacity.getText().toString().trim();
                String budget = eBudget.getText().toString().trim();
                String email = eEmail.getText().toString().trim();
                String phone = ePhone.getText().toString().trim();
                String description = eDescription.getText().toString().trim();

                if (eName.getText().toString().length() == 0){
                    error =  error + "Name is required!";
                    eName.setText(error);
                }
                if (eEmail.getText().toString().length() == 0){
                    error =  error + "Email is required!";
                    eEmail.setText(error);

                }
                if (ePlace.getText().toString().length() == 0){
                    error =  error + "Place is  required!";
                    ePlace.setText(error);
                }
                if (eDateTime.getText().toString().length() == 0){
                    error =  error + "Date & time is  required!";
                    eDateTime.setText(error);
                }
                if (eBudget.getText().toString().length() == 0){
                    error =  error + "Budget is  required!";
                    eBudget.setText(error);
                }
                if (ePhone.getText().toString().length() == 0){
                    error =  error + "Phone number is  required!";
                    ePhone.setText(error);
                }
                error1.setText(error);




                String value = "Name:" + name + " PlaceName:" + place + "\n Type:" +isOnlineSelected+"\n Time: "+time+ "\n Capacity:" + capacity + "\n Budget" + budget + "\n Email:" + email + "\n Phone" + phone+"\n Description:"+description;
                if (name.length() > 0) {
                    value += "-::-" + name;
                }

                Intent current = getIntent();
                String key = current.getStringExtra("key");
                if (key != null && !key.isEmpty()) {
                    //                    Util.getInstance().setKeyValue(MainActivity.this, key, value);
                }

            }
        });
    }
}