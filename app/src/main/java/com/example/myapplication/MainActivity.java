package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private EditText et1, etm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.text_value1);
        etm = (EditText) findViewById(R.id.text_mul);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void saveChared(View view){
        String name = et1.getText().toString();
        String datos = etm.getText().toString();

        SharedPreferences preferens = getSharedPreferences("dato", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferens.edit();
        editor.putString(name, datos);
        editor.commit();
        finish();
    }

    public void searh(View view){
        String name = et1.getText().toString();
        SharedPreferences preferences = getSharedPreferences("dato", Context.MODE_PRIVATE);
        String datos = preferences.getString(name, "");

        if(datos.length() != 0){
            etm.setText(preferences.getString(name, ""));
        }else{
            Toast.makeText(this,"NO hay registros con el nombre", Toast.LENGTH_SHORT).show();
        }
    }
}