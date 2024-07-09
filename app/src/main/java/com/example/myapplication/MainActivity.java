package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private RadioGroup rg;
    private RadioButton rb1, rb2, rb3, rb4;
    private TextView twr;
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.value1);
        et2 = (EditText)findViewById(R.id.value2);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        rb1 = (RadioButton)findViewById(R.id.radioButton);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);
        rb4 = (RadioButton)findViewById(R.id.radioButton4);
        twr = (TextView)findViewById(R.id.textView2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rb1.getId() == i){
                    //twr.setText(String.valueOf("valor: "+rb1.getText()));
                    operation = "S";
                }else if(rb2.getId() == i){
                    //twr.setText(String.valueOf("valor: "+rb2.getText()));
                    operation = "R";
                } else if (rb3.getId() == i) {
                    operation = "M";
                } else if (rb4.getId() == i) {
                    operation = "D";
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void checked(View view){
        String num1 = et1.getText().toString();
        String num2 = et2.getText().toString();
        float res = 0;

        if(num1 == null || num1.isEmpty() || num2 == null || num2.isEmpty()){
            Toast.makeText(this, "campo vacio", Toast.LENGTH_SHORT).show();
            twr.setText(String.valueOf(0.0));
            return;
        }

        switch (operation){
            case "S":
                res = (Float.parseFloat(num1) + Float.parseFloat(num2));
                twr.setText(String.valueOf(res));
                break;
            case "R":
                res = (Float.parseFloat(num1) - Float.parseFloat(num2));
                twr.setText(String.valueOf(res));
                break;
            case "M":
                res = (Float.parseFloat(num1) * Float.parseFloat(num2));
                twr.setText(String.valueOf(res));
                break;
            case "D":
                if(Float.parseFloat(num2) != 0){
                    res = (Float.parseFloat(num1) / Float.parseFloat(num2));
                    twr.setText(String.valueOf(res));
                }else{
                    Toast.makeText(this, "el valor del campo 2 no es valido!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this, "Selecione una operacion", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}