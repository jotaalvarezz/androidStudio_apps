package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private CheckBox check_sum;
    private CheckBox check_res;
    private CheckBox check_mul;
    private CheckBox check_div;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.input_num1);
        et2 = (EditText)findViewById(R.id.input_num2);
        check_sum = (CheckBox)findViewById(R.id.check_suma);
        check_res = (CheckBox)findViewById(R.id.check_resta);
        check_mul = (CheckBox)findViewById(R.id.check_mul);
        check_div = (CheckBox)findViewById(R.id.check_div);
        res = (TextView)findViewById(R.id.text_res);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void  calculate(View view){
        ArrayList<String> operations = new ArrayList<>();
        float value1 = Float.parseFloat(et1.getText().toString());
        float value2 = Float.parseFloat(et2.getText().toString());

        if(check_sum.isChecked()){
            operations.add("sumar");
        }

        if(check_res.isChecked()){
            operations.add("restar");
        }

        if(check_mul.isChecked()){
            operations.add("multiplicar");
        }

        if(check_div.isChecked()){
            operations.add("division");
        }

        if(et1.getText().toString() == null){
            Toast.makeText(this, "¡No hay valor en el campo uno!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(et2.getText().toString() == null){
            Toast.makeText(this, "¡No hay valor en el campo dos!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(operations.size() == 0){
            Toast.makeText(this, "¡No hay ninguna operacion selecionada!", Toast.LENGTH_SHORT).show();
            return;
        }

        String cadena = "";

        for (String operation: operations) {
            if(operation == "sumar"){
                float result = value1 + value2;
                cadena = cadena+"/"+operation+": "+result;
            }
            if(operation == "restar"){
                float result = value1 - value2;
                cadena = cadena+"/"+operation+": "+result;
            }
            if(operation == "multiplicar"){
                float result = value1 * value2;
                cadena = cadena+"/"+operation+": "+result;
            }
            if(operation == "division"){
                if(value2 != 0){
                    float result = value1 / value2;
                    cadena = cadena+"/"+operation+": "+result;
                }else{
                    Toast.makeText(this, "Valor "+value2+" en el campo dos no valido", Toast.LENGTH_SHORT).show();
                }
            }
        }

        res.setText(String.valueOf(cadena));
    }
}