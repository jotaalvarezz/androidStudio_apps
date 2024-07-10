package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.sql.Array;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private Spinner spinner;
    private TextView tw_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.input_num1);
        et2 = (EditText)findViewById(R.id.input_num2);
        tw_res = (TextView)findViewById(R.id.txt_res);
        spinner = (Spinner)findViewById(R.id.spinner);

        String options[] = {"Sumar","Restar","Multiplicar","Dividir"};
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_jesus, options);
        spinner.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calculate(View view){
        String num1 = et1.getText().toString();
        String num2 = et2.getText().toString();
        String selection = spinner.getSelectedItem().toString();

        if(num1 == null || num1.isEmpty() || num2 == null || num2.isEmpty()){
            Toast.makeText(this, "¡Campo vacio!", Toast.LENGTH_SHORT).show();
            return;
        }

        float res = 0;

        switch (selection){
            case "Sumar":
                res = Float.parseFloat(num1) + Float.parseFloat(num2);
                tw_res.setText(String.valueOf(res));
                break;
            case "Restar":
                res = Float.parseFloat(num1) - Float.parseFloat(num2);
                tw_res.setText(String.valueOf(res));
                break;
            case "Multiplicar":
                res = Float.parseFloat(num1) * Float.parseFloat(num2);
                tw_res.setText(String.valueOf(res));
                break;
            case "Dividir":
                if(Float.parseFloat(num2) != 0){
                    res = Float.parseFloat(num1) / Float.parseFloat(num2);
                    tw_res.setText(String.valueOf(res));
                }else{
                    Toast.makeText(this, "Valor 2 no valido ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}