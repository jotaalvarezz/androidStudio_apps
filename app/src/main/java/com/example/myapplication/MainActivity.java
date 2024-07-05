package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private EditText edt1;
    private EditText edt2;
    private EditText edt3;
    private EditText edt4;
    private TextView tw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edt1 = (EditText) findViewById(R.id.text_value1);
        edt2 = (EditText) findViewById(R.id.text_value2);
        edt3 = (EditText) findViewById(R.id.text_value3);
        edt4 = (EditText) findViewById(R.id.editTextText);
        tw1 = (TextView) findViewById(R.id.textView6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calculate(View view){
        String value1 = edt1.getText().toString();
        String value2 = edt2.getText().toString();
        String value3 = edt3.getText().toString();

        float prom = 0;

        float note1 = Float.parseFloat(value1);
        float note2 = Float.parseFloat(value2);
        float note3 = Float.parseFloat(value3);

        prom = (note1 + note2 + note3) / 3;

        String res = "";
        String promedio = String.valueOf(prom);
        if(prom >= 6){
            res = String.valueOf("aprobado");
        }else if(prom < 6){
            res = String.valueOf("reprobado");
        }

        tw1.setText(res);
        edt4.setText(promedio);
    }
}