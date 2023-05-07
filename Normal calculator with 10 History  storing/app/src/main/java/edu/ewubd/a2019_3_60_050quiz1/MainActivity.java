package edu.ewubd.a2019_3_60_050quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView inputText, outputText, textfield;
    private String input,output, newOutput, arrylist;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDel;
    Button btnDiv, btnMulti, btnSub, btnPoint, btnEqual, btnAdd, btnHis1,btnHis2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        inputText = findViewById(R.id.tvEquation);
        outputText = findViewById(R.id.vsol);
        textfield = findViewById(R.id.textfield);

        assignId(btn0, R.id.btn0);
        assignId(btn1, R.id.btn1);
        assignId(btn2, R.id.btn2);
        assignId(btn3, R.id.btn3);
        assignId(btn4, R.id.btn4);
        assignId(btn5, R.id.btn5);
        assignId(btn6, R.id.btn6);
        assignId(btn7, R.id.btn7);
        assignId(btn8, R.id.btn8);
        assignId(btn9, R.id.btn9);
        assignId(btnDel, R.id.btnDel);
        assignId(btnDiv, R.id.btnDiv);
        assignId(btnMulti, R.id.btnMulti);
        assignId(btnSub, R.id.btnSub);
        assignId(btnPoint, R.id.btnPoint);
        assignId(btnEqual, R.id.btnEqual);
        assignId(btnAdd, R.id.btnAdd);
        assignId(btnHis1, R.id.btnHis1);
        assignId(btnHis2, R.id.btnHis2);







    }

    void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sp = getSharedPreferences("History",MODE_PRIVATE);
        SharedPreferences.Editor speditor = sp.edit();


        //variable diclaring for sharepreferences
        String history1 = sp.getString("history1","").toString();
        String history2 = sp.getString("history2","").toString();
        int count = sp.getInt("count", 0);
        String history3 = sp.getString("history3","").toString();
        String history4 = sp.getString("history4","").toString();
        String history5 = sp.getString("history5","").toString();
        String history6 = sp.getString("history6","").toString();
        String history7 = sp.getString("history7","").toString();
        String history8 = sp.getString("history8","").toString();
        String history9 = sp.getString("history9","").toString();
        String history10 = sp.getString("history10","").toString();

        Button btn = (Button) v;

        String buttonText = btn.getText().toString();
        if (buttonText.equals("delete")){
            outputText.setText("");
            inputText.setText("");
            input = null;
            return;
        }

        switch (buttonText) {
            case "*":
                solve();
                input += "*";
                break;

            case "<":
                String[] myArray = {history1, history2,history3,history4,history5,history6,history7,history8,history9,history10};
                try {
                    textfield.setText(myArray[count]);
                    if(count == 0){
                        speditor.putInt("count", 0);
                        speditor.apply();
                        break;
                    }else{
                        speditor.putInt("count", count-1);
                        speditor.apply();
                        break;
                    }
                }catch (Exception e){
                    outputText.setText(e.getMessage().toString());
                }

            case ">":
                String[] myArray1 = {history1, history2,history3,history4,history5,history6,history7,history8,history9,history10};
                try {
                    textfield.setText(myArray1[count]);
                    if(count == 9){
                        speditor.putInt("count", 9);
                        speditor.apply();
                        break;
                    }else{
                        speditor.putInt("count", count+1);
                        speditor.apply();
                        break;
                    }
                }catch (Exception e){
                    outputText.setText(e.getMessage().toString());
                }



            case "=":

                if(history1.isEmpty()) {
                    speditor.putString("history1", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 1", Toast.LENGTH_SHORT).show();
                    solve();
                    break;
                }
                 else if (history2.isEmpty()){
                    speditor.putString("history2", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 2", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history3.isEmpty()){
                    speditor.putString("history3", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 3", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history4.isEmpty()){
                    speditor.putString("history4", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 4", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history5.isEmpty()){
                    speditor.putString("history5", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 5", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history6.isEmpty()){
                    speditor.putString("history6", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 6", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history7.isEmpty()){
                    speditor.putString("history7", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 7", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history8.isEmpty()){
                    speditor.putString("history8", input);
                   speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 8", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history9.isEmpty()){
                    speditor.putString("history9", input);
                    speditor.apply();
                    Toast.makeText(MainActivity.this, "Store 9", Toast.LENGTH_SHORT).show();
                    solve();
                    break;

                }
                else if (history10.isEmpty()){
                    speditor.putString("history10", input);
                    speditor.apply();
                  Toast.makeText(MainActivity.this, "Store 10", Toast.LENGTH_SHORT).show();
                    solve();
                   break;

               }
                else{
                    Toast.makeText(MainActivity.this, "Reaching the limit", Toast.LENGTH_SHORT).show();
                    speditor.putString("history1", "");
                    speditor.putString("history2", "");
                    speditor.putString("history3", "");
                    speditor.putString("history4", "");
                    speditor.putString("history5", "");
                    speditor.putString("history6", "");
                    speditor.putString("history7", "");
                    speditor.putString("history8", "");
                    speditor.putString("history9", "");
                    speditor.putString("history10", "");
                    speditor.apply();
                    break;



                }



            default:
                if (input == null) {
                    input = "";
                }
                if (buttonText.equals("+") || buttonText.equals("/") || buttonText.equals("-")) {
                    solve();
                }
                input += buttonText;
        }
        inputText.setText(input);
    }

    private void solve() {
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e) {
                outputText.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])){
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText("-" + newOutput);
                    input = d +"";
                }
                else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = d + "";
                }
            }catch (Exception e){
                outputText.setText(e.getMessage().toString());
            }
        }
    }
    private String cutDecimal(String number){
        String n [] = number.split("\\.");
        if (n.length >1){
            if (n[1].equals("0")){
                number = n[0];
            }
        }
        return number;
    }
}