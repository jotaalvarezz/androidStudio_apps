package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn1 = (ImageButton) findViewById(R.id.bananas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Frutas(View view){
        int id = view.getId();
        if (id == R.id.bananas) {
            Toast.makeText(this, "estas son bananas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.fresas) {
            Toast.makeText(this, "estas son fresas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.cerezas) {
            Toast.makeText(this, "estas son cerezas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.frambuesas) {
            Toast.makeText(this, "estas son frambuesas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.kiwis) {
            Toast.makeText(this, "estos son kiwis", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.mangos) {
            Toast.makeText(this, "estos son mangos", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.manzanas) {
            Toast.makeText(this, "estas son manzanas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.melon) {
            Toast.makeText(this, "estos son melones", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.naranjas) {
            Toast.makeText(this, "estas son naranjas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.peras) {
            Toast.makeText(this, "estas son peras", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.pina) {
            Toast.makeText(this, "estas son piñas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.sandia) {
            Toast.makeText(this, "estas son sandias", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.uvas) {
            Toast.makeText(this, "estas son uvas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.zarzamora) {
            Toast.makeText(this, "estas son zarzamoras", Toast.LENGTH_SHORT).show();
        }
    }
}