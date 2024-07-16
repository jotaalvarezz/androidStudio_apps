package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText etm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.text_search);
        etm = (EditText)findViewById(R.id.text_mul);
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
        String name = et1.getText().toString();
        String content = etm.getText().toString();

        File registerSD = Environment.getExternalStorageDirectory();
        String directory = registerSD.getPath();
        File directoryFile = new File(directory, name);

        Toast.makeText(this,"ruta : "+directory, Toast.LENGTH_SHORT).show();
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput(name,Activity.MODE_PRIVATE));
            if(!content.isEmpty()){
                file.write(content);
                file.flush();
                file.close();
                Toast.makeText(this,"¡Bitacora Creada!", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this,"error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void search(View view ){
        String name = et1.getText().toString();

        File registerSD = Environment.getExternalStorageDirectory();
        String directory = registerSD.getPath();
        File directoryFile = new File(directory, name);

        try {
            InputStreamReader file = new InputStreamReader(openFileInput(name));
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();
            String info = "";
            while (line != null){
                info = info + line + "\n";
                line = br.readLine();
            }
            br.close();
            file.close();
            etm.setText(info);
        } catch (IOException e) {
            Toast.makeText(this,"error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}