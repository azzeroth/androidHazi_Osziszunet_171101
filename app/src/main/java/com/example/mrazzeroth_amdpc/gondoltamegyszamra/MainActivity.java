package com.example.mrazzeroth_amdpc.gondoltamegyszamra;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Button positiveBtn, negativeBtn, sendBtn;
    private ImageView[] healthImg = new ImageView[5];
    private TextView number;
    private int Health = 5;
    private int szam = 0, randomSzam = 0;
    private Random rnd = new Random();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializalas();
        dialog = new AlertDialog.Builder(MainActivity.this);
        randomSzam = rnd.nextInt(50)+1;

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (szam == randomSzam){
                    showAlert("Nyertél!", "Szeretnél új játékot kezdeni?", "Igen", "Nem");
                }
                else if (szam < randomSzam){
                    Toast.makeText(MainActivity.this, "Feljebb", Toast.LENGTH_SHORT).show();
                    Health--;
                    setHealth(Health);
                }
                else if (szam > randomSzam){
                    Toast.makeText(MainActivity.this, "Lejjebb", Toast.LENGTH_SHORT).show();
                    Health--;
                    setHealth(Health);
                }
                if(Health == 0){
                    showAlert("Majd legközelebb!", "Szeretnél új játékot kezdeni?", "Igen", "Nem");
                }
            }
        });

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (szam < 50){
                    szam++;
                    number.setText(szam+"");
                }
            }
        });

        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (szam > 0){
                    szam--;
                    number.setText(szam+"");
                }
            }
        });
    }

    public void setHealth(int health){
        for(int i = 0; i < 5;i++){
            if(i < health)
                healthImg[i].setBackgroundResource(R.drawable.heart2);
            else
                healthImg[i].setBackgroundResource(R.drawable.heart1);

        }
    }

    public void resetGame(){
        setHealth(5);
        Random rnd = new Random();
        randomSzam = rnd.nextInt(50)+1;
        szam = 0;
        number.setText(szam+"");
        Health = 5;
    }

    public void inicializalas(){
        sendBtn = (Button) findViewById(R.id.sendBtn);
        positiveBtn = (Button) findViewById(R.id.positiveBtn);
        negativeBtn = (Button) findViewById(R.id.negativeBtn);

        number = (TextView) findViewById(R.id.SzamTv);
        healthImg[0] = (ImageView) findViewById(R.id.hp1Img);
        healthImg[1] = (ImageView) findViewById(R.id.hp2Img);
        healthImg[2] = (ImageView) findViewById(R.id.hp3Img);
        healthImg[3] = (ImageView) findViewById(R.id.hp4Img);
        healthImg[4] = (ImageView) findViewById(R.id.hp5Img);

    }

    public void showAlert(String title, String text, String btn1, String btn2){
        dialog.setTitle(title).setMessage(text).setPositiveButton(btn1, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                resetGame();
            }
        })
        .setNegativeButton(btn2, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        })
        .setCancelable(false).create();
        dialog.show();
    }
}
