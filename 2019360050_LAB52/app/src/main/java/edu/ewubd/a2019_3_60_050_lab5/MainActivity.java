package edu.ewubd.a2019_3_60_050_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TableRow namell,emailll,phonell,pass2ll;
    private TextView showtxt,toggletxt,tittletxt;
    private EditText uname,uemail,upass,upass2,uphone,user_id;
    private CheckBox remUserid,remLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        namell = findViewById(R.id.namell);
        emailll = findViewById(R.id.emailll);
        phonell = findViewById(R.id.phonell);
        pass2ll = findViewById(R.id.pass2ll);
        showtxt = findViewById(R.id.showtxt);
        toggletxt = findViewById(R.id.toggle);
        tittletxt = findViewById(R.id.tittletxt);
        uname = findViewById(R.id.name);
        uemail = findViewById(R.id.mail);
        upass = findViewById(R.id.pass);
        upass2 = findViewById(R.id.pass2);
        uphone = findViewById(R.id.phone);
        user_id = findViewById(R.id.user_id);
        remUserid = findViewById(R.id.remuserid);
        remLogin = findViewById(R.id.remlogin);
        SharedPreferences sp = getSharedPreferences("useraccount",MODE_PRIVATE);
        SharedPreferences.Editor speditor = sp.edit();


        //variable diclaring for sharepreferences
        String userexist = sp.getString("userid","").toString();
        boolean isrememberedLogin = sp.getBoolean("isremLogin",false);
        boolean isrememberedId = sp.getBoolean("isremUserid",false);

        if(!userexist.isEmpty()){
            //if remeberLogin is chacked.
            if(isrememberedLogin){
                // send to CreateEvent...
                goToNewEventActivity();
            }
            //showing Login page . becourse, user careat account.
            else{
                namell.setVisibility(View.GONE);
                emailll.setVisibility(View.GONE);
                phonell.setVisibility(View.GONE);
                pass2ll.setVisibility(View.GONE);
                showtxt.setText("Don't Have an Account?");
                toggletxt.setText("SignUp");
                tittletxt.setText("Login");

                if(isrememberedId){
                    user_id.setText(sp.getString("userid","").toString());
                }
            }
        }


        findViewById(R.id.toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = ((Button)view).getText().toString();


                if(txt.equalsIgnoreCase("Login")){
                    namell.setVisibility(View.GONE);
                    emailll.setVisibility(View.GONE);
                    phonell.setVisibility(View.GONE);
                    pass2ll.setVisibility(View.GONE);
                    showtxt.setText("Don't Have an Account?");
                    toggletxt.setText("SignUp");
                    tittletxt.setText("Login");
                }else{
                    namell.setVisibility(View.VISIBLE);

                    emailll.setVisibility(View.VISIBLE);
                    phonell.setVisibility(View.VISIBLE);
                    pass2ll.setVisibility(View.VISIBLE);
                    showtxt.setText("Already Have an Account?");
                    toggletxt.setText("Login");
                    tittletxt.setText("Signup");
                }
            }
        });
        findViewById(R.id.exit).setOnClickListener(view -> finish());
        findViewById(R.id.go).setOnClickListener(view -> {
            String txt = toggletxt.getText().toString();

            if(txt.equalsIgnoreCase("Login")){
                //get info...Do check...Store...
                String userid = user_id.getText().toString();
                String name = uname.getText().toString();
                String pass = upass.getText().toString();
                String pass2 = upass2.getText().toString();
                String phone = uphone.getText().toString();
                String  email= uemail.getText().toString();
                boolean isremUserid = remUserid.isChecked();
                boolean isremLogin = remLogin.isChecked();
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || userid.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else if (!pass.equals(pass2)) {
                    Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }else{
                    speditor.putString("userid",userid);
                    speditor.putString("name",name);
                    speditor.putString("pass",pass);
                    speditor.putString("phone",phone);
                    speditor.putString("email",email);
                    speditor.putBoolean("isremLogin", isremUserid);
                    speditor.putBoolean("isremUserid", isremLogin);
                    //complete the full..
                    speditor.apply();
                    // go to another page....
                    goToNewEventActivity();
                    finish();
                }
            }
            else{

                //get info...
                String userid = user_id.getText().toString().trim();
                String pass = upass.getText().toString().trim();
                boolean isremUserid = remUserid.isChecked();
                boolean isremLogin = remLogin.isChecked();
                String saveduserId = sp.getString("userid","");
                String savedPass = sp.getString("pass","");
                speditor.putBoolean("isremUserid", isremUserid);
                speditor.putBoolean("isremLogin", isremLogin);
                //complete the full..
                speditor.apply();
                if (!userid.equals(saveduserId) || !pass.equals(savedPass)) {
                    Toast.makeText(MainActivity.this, "Invalid user ID or password", Toast.LENGTH_SHORT).show();
                }else {
                    // Go to new event activity
                    goToNewEventActivity();
                    finish();
                    // close this activity
                }
            }
        });
    }     private void goToNewEventActivity() {
        // Start new event activity
        Intent I = new Intent(this,CreateEcentActivity.class);
        Bundle bundle = new Bundle();
        String  email= uemail.getText().toString();
        String userid = user_id.getText().toString();
        bundle.putString( "email",email);
        bundle.putString( "userid",userid);
        I.putExtras(bundle);
        startActivity(I);
        finish();
        // startActivity(MainActivity.newIntent(this));
    }
}