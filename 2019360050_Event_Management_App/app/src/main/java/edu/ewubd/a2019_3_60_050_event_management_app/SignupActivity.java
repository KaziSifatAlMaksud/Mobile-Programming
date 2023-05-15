package edu.ewubd.a2019_3_60_050_event_management_app;


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

public class SignupActivity extends AppCompatActivity {

    private TableRow namell,emailll,phonell,pass2ll;
    private TextView showtxt,toggletxt,tittletxt;
    private EditText uname,uemail,upass,upass2,uphone,user_id;
    private CheckBox remUserid,remLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
               // goToNewEventActivity();
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
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else if (!pass.equals(pass2)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(SignupActivity.this, "Invalid user ID or password", Toast.LENGTH_SHORT).show();
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
        Intent I = new Intent(this,UpcommingEvent.class);
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







/*


import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import android.util.Patterns;

public class SignupActivity extends AppCompatActivity {
    private Button btnToggle,btnGo,btnExit;
    private EditText etName, etMail, etPhone, etPassword, etRepassword, etUserid;
    private TextView tvTitle,tvExist;
    private LinearLayout llName, llMail, llPhone, llPassword, llRepassword, llUserid, llExist, llRemid, llRemlogin;
    private CheckBox chkRemid,chkRemlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("user_account", MODE_PRIVATE);
        boolean is_login_check = sp.getBoolean("remlogin", false);
        if(is_login_check){
            Intent i = new Intent (SignupActivity.this, CreateEventActivity.class);
            startActivity(i);
            return;
        }
        boolean is_userid_check = sp.getBoolean("remid", false);
        setContentView(R.layout.activity_signup);
        btnToggle = findViewById(R.id.btnToggle);
        btnExit = findViewById(R.id.btnExit);
        btnGo = findViewById(R.id.btnGo);
        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etRepassword = findViewById(R.id.etRepassword);
        etUserid = findViewById(R.id.etUserid);
        tvTitle = findViewById(R.id.tvTitle);
        tvExist = findViewById(R.id.tvExist);
        llName = findViewById(R.id.llName);
        llMail = findViewById(R.id.llMail);
        llPhone = findViewById(R.id.llPhone);
        llPassword = findViewById(R.id.llPassword);
        llRepassword = findViewById(R.id.llRepassword);
        llUserid = findViewById(R.id.llUserid);
        llExist = findViewById(R.id.llExist);
        llRemid = findViewById(R.id.llRemid);
        llRemlogin = findViewById(R.id.llRemlogin);
        chkRemid = findViewById(R.id.chkRemid);
        chkRemlogin = findViewById(R.id.chkRemlogin);


        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toggleValue = btnToggle.getText().toString();
                boolean islogin = toggleValue.equalsIgnoreCase("login");
                if(islogin){
                    tvTitle.setText("Login");
                    tvExist.setText("Don't Have an Account");
                    btnToggle.setText("signup");
                    //hide field
                    llName.setVisibility(View.GONE);
                    llMail.setVisibility(View.GONE);
                    llPhone.setVisibility(View.GONE);
                    llRepassword.setVisibility(View.GONE);
                }
                else{
                    tvTitle.setText("SignUp");
                    tvExist.setText("Already Have an Account?");
                    btnToggle.setText("Login");
                    llName.setVisibility(View.VISIBLE);
                    llMail.setVisibility(View.VISIBLE);
                    llPhone.setVisibility(View.VISIBLE);
                    llRepassword.setVisibility(View.VISIBLE);
                }
            }
        });
        if(is_userid_check){
            btnToggle.callOnClick();
            etUserid.setText(sp.getString("user_id", ""));
        }

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toggleValue = btnToggle.getText().toString();
                boolean islogin = toggleValue.equalsIgnoreCase("login");
                boolean isremid = chkRemid.isChecked();
                boolean isremlogin = chkRemlogin.isChecked();

                if(islogin){

                    String error = "";
                    String name_text = etName.getText().toString();
                    String mail_text = etMail.getText().toString();
                    String phone_text = etPhone.getText().toString();
                    String userid_text = etUserid.getText().toString();
                    String password_text = etPassword.getText().toString();
                    String repassword_text = etRepassword.getText().toString();
                    if(name_text.isEmpty() || mail_text.isEmpty() || phone_text.isEmpty() || userid_text.isEmpty() || password_text.isEmpty() || repassword_text.isEmpty()){
                        error += "Please Fill-up All Fields\n";
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(mail_text).matches()){
                        error += "Invalid Email\n";
                    }
                    if (!phone_text.startsWith("01") || phone_text.length()!=11) {
                        error += "Phone Invalid\n";
                    }
                    if(!password_text.equals(repassword_text)){
                        error += "Password Did Not match\n";
                    }
                    if(error.length()>0){
                        Toast.makeText(SignupActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                    else {
                        SharedPreferences sp = getSharedPreferences("user_account", MODE_PRIVATE);
                        SharedPreferences.Editor spEditor = sp.edit();
                        spEditor.putString("name", name_text);
                        spEditor.putString("mail", mail_text);
                        spEditor.putString("phone", phone_text);
                        spEditor.putString("user_id", userid_text);
                        spEditor.putString("password", password_text);
                        spEditor.putBoolean("remid", isremid);
                        spEditor.putBoolean("remlogin", isremlogin);
                        spEditor.apply();
                        etName.setText("");etMail.setText("");etPhone.setText("");etUserid.setText("");etPassword.setText("");etRepassword.setText("");
                        Intent i = new Intent (SignupActivity.this, CreateEventActivity.class);
                        startActivity(i);
                    }
                }
                else{

                    String userid_text = etUserid.getText().toString();
                    String password_text = etPassword.getText().toString();
                    SharedPreferences sp = getSharedPreferences("user_account", MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sp.edit();
                    String user_id_check = sp.getString("user_id", "");
                    String password_check = sp.getString("password", "");
                    if(user_id_check.equals(userid_text) && password_check.equals(password_text)){
                        spEditor.putBoolean("remid", isremid);
                        spEditor.putBoolean("remlogin", isremlogin);
                        spEditor.apply();
                        etUserid.setText("");etPassword.setText("");
                        Intent i = new Intent (SignupActivity.this, CreateEventActivity.class);
                        i.putExtra("userid", userid_check);
                        i.putExtra("mail", mail_check);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "User Authentication Failed", Toast.LENGTH_LONG).show();
                    }
                }
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

}*/
