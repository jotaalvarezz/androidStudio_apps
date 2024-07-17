package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    EditText et_code, et_description, et_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        et_code = (EditText)findViewById(R.id.text_code);
        et_description = (EditText)findViewById(R.id.text_description);
        et_price = (EditText)findViewById(R.id.text_price);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void save(View view){
         String code = et_code.getText().toString();
         String description = et_description.getText().toString();
         String price = et_price.getText().toString();

         AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
         SQLiteDatabase db = admin.getWritableDatabase();

         if(!code.isEmpty() || !description.isEmpty() || !price.isEmpty()){
             ContentValues registro = new ContentValues();
             registro.put("codigo",code);
             registro.put("descripcion", description);
             registro.put("precio", price);
             db.insert("articulos", null, registro);
             db.close();
             et_code.setText("");
             et_description.setText("");
             et_price.setText("");
             Toast.makeText(this, "articulo creado", Toast.LENGTH_SHORT).show();
         } else{
             Toast.makeText(this, "hay un campo vacio", Toast.LENGTH_SHORT).show();
         }
    }

    public void show(View view){
        String code = et_code.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!code.isEmpty()){
            Cursor file = db.rawQuery("SELECT descripcion, precio FROM articulos WHERE codigo = "+code, null);

            if(file.moveToFirst()){
                et_description.setText(file.getString(0));
                et_price.setText(file.getString(1));
                db.close();
            } else {
                Toast.makeText(this, "No hay registros con la refencia", Toast.LENGTH_SHORT).show();
                db.close();
            }
        } else {
            Toast.makeText(this, "hay un campo vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view){
        String codigo = et_code.getText().toString();
        String descripcion = et_description.getText().toString();
        String precio = et_price.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!codigo.isEmpty() || !descripcion.isEmpty() || !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int amount = db.update("articulos", registro, "codigo="+codigo, null);
            db.close();

            if(amount == 1) {
                Toast.makeText(this, "Articulo modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Articulo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void destroy(View view){
        String codigo = et_code.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if(!codigo.isEmpty()){
            int amount = db.delete("articulos", "codigo="+codigo,null);
            db.close();
            et_code.setText("");
            et_description.setText("");
            et_price.setText("");

            if(amount == 1){
                Toast.makeText(this, "Articulo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debe introducir codigo del articulo", Toast.LENGTH_SHORT).show();
        }
    }
}