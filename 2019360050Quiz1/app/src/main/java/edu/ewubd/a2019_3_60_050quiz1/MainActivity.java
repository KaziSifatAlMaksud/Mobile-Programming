package edu.ewubd.a2019_3_60_050quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView inputText, outputText;
    private String input,output, newOutput;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDel;
    Button btnDiv, btnMulti, btnSub, btnPoint, btnEqual, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.tvEquation);
        outputText = findViewById(R.id.vsol);

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

    }

    void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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

            case "=":
                solve();
                break;

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