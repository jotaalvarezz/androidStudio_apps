package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
    private ListView lv;
    private TextView txt_res;
    private Object[][] personas = {
            {"jesus", "alvarez", 29},
            {"celso", "piña", 36},
            {"juan", "salcedo", 34},
            {"lisando", "pacheco", 41},
            {"lorena", "del rio", 32}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txt_res = (TextView)findViewById(R.id.txt_res);
        lv = (ListView)findViewById(R.id.list_id);
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < personas.length; i++) {
            Persona persona = new Persona((String)personas[i][0],(String)personas[i][1],(int)personas[i][2]);
            names.add(persona.getName()+" "+persona.getLast_name());
        }
        ArrayAdapter<String> persons = new ArrayAdapter<>(this, R.layout.simple_list_item_jesus,names);
        lv.setAdapter(persons);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txt_res.setText(String.valueOf("nombre: "+lv.getItemAtPosition(i)+", edad:"+personas[i][2]));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}