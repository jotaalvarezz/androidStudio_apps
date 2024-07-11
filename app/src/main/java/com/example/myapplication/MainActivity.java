package com.example.myapplication;

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
    private EditText et1;
    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.txt_user);
        et2 = (EditText)findViewById(R.id.txt_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Login(View view){
        String user = et1.getText().toString();
        String pass = et2.getText().toString();

        if(user.isEmpty() || user == null){
            Toast.makeText(this, "¡Campo Usuario vacio!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.isEmpty() || pass == null){
            Toast.makeText(this, "¡Campo Contraseña vacio!", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this,"Autenticacion en proceso...",Toast.LENGTH_SHORT).show();
    }
}