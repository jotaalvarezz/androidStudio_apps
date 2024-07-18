package com.example.myapplication;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button play;
    SoundPool sp;
    int sonido_de_Reproduccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        play = (Button)findViewById(R.id.button_play);
        // Inicializar SoundPool
        sp = new SoundPool.Builder()
                .setMaxStreams(1)
                .build();

        // Cargar el sonido
        sonido_de_Reproduccion = sp.load(this, R.raw.sound_short, 1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AudioSoundPool(View view){
        // Verificar si el sonido se ha cargado antes de reproducir
        if (sonido_de_Reproduccion != 0) {
            sp.play(sonido_de_Reproduccion, 1, 1, 1, 0, 1f);
        } else {
            Toast.makeText(this, "Error al cargar el sonido", Toast.LENGTH_SHORT).show();
        }
    }

    public void AudiomediaPlayer(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_long);
        mp.start();
    }
}