package com.example.myapplication;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button play, repeat;
    ImageView iv;
    MediaPlayer mp;
    int sonido_de_Reproduccion, repetir = 2, position = 0;
    MediaPlayer vectormp [] = new MediaPlayer[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        play = (Button)findViewById(R.id.btn_play);
        repeat = (Button)findViewById(R.id.btn_repeat);
        iv = (ImageView)findViewById(R.id.imageView);

        vectormp[0] = MediaPlayer.create(this, R.raw.race);
        vectormp[1] = MediaPlayer.create(this, R.raw.sound);
        vectormp[2] = MediaPlayer.create(this, R.raw.tea);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void PlayPause(View view){
      if(vectormp[position].isPlaying()){
          vectormp[position].pause();
          play.setBackgroundResource(R.drawable.reproducir);
          Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
      } else {
          vectormp[position].start();
          play.setBackgroundResource(R.drawable.pausa);
          Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
      }
    }

    public void Stop(View view) {
        if (vectormp[position] != null){
            //stop vacea la posicion actual
            vectormp[position].stop();
            //por lo tanto se debe indicar despues las canciones nuevamente
            vectormp[0] = MediaPlayer.create(this, R.raw.race);
            vectormp[1] = MediaPlayer.create(this, R.raw.sound);
            vectormp[2] = MediaPlayer.create(this, R.raw.tea);
            position = 0;
            play.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    public void Repeat(View view){
        if(repetir == 1){
            repeat.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No Repetir", Toast.LENGTH_SHORT).show();
            vectormp[position].setLooping(false);
            repetir = 2;
        }else{
            repeat.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[position].setLooping(true);
            repetir = 1;
        }
    }

    public void Next(View view){
        if(position < vectormp.length - 1){
            if(vectormp[position].isPlaying()){
                vectormp[position].stop();
                position++;
                vectormp[position].start();
                switch (position){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }
            }else{
                position++;
                switch (position){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }
            }
        } else {
            Toast.makeText(this, "No hay Canciones", Toast.LENGTH_SHORT).show();
        }
    }

    public void Back(View view){
        if(position >= 1){
            if(vectormp[position].isPlaying()){
                vectormp[position].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.race);
                vectormp[1] = MediaPlayer.create(this, R.raw.sound);
                vectormp[2] = MediaPlayer.create(this, R.raw.tea);
                position--;
                vectormp[position].start();
                switch (position){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }
            }else{
                position--;
                switch (position){
                    case 0:
                        iv.setImageResource(R.drawable.portada1);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.portada2);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.portada3);
                        break;
                }
            }
        }
    }
}