package com.example.myapplication;

import android.app.Activity;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private EditText etm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etm = (EditText)findViewById(R.id.text_mul);
        //fileList trae un Array con todos los archivos almacenados en el dispositivo
        String files[] = fileList();

        if (FileExist(files, "bitacora.txt")){
            //La clase InputStreamReader nos permite abrir un archivo para poder leerlo
            try {
                InputStreamReader file = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(file);
                String line = br.readLine();
                String bitacora = "";
                while (line != null){
                    bitacora = bitacora + line + "\n";
                    line = br.readLine();
                }
                br.close();
                file.close();
                etm.setText(bitacora);
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean FileExist(String[] files, String name_file){
        for (int i = 0; i < files.length; i++) {
            if(files[i].equals(name_file)){
                return true;
            }
        }
        return false;
    }

    public void save(View view){
        //OutputStreamWriter no ayuda a mandar texto que vamos a escribir en un archivo
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            file.write(etm.getText().toString());
            file.flush();
            file.close();
        } catch (IOException e) {

        }
        Toast.makeText(this, "Bitacora guardada correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}