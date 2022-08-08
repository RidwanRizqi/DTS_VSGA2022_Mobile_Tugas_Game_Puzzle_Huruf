package com.example.dts_tugas_game_puzzle_huruf;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[16];
    int size = 16;
    Game game = new Game(buttons, null);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // deklarasi button

        game.buttons[0] = (Button) findViewById(R.id.button1);
        game.buttons[1] = (Button) findViewById(R.id.button2);
        game.buttons[2] = (Button) findViewById(R.id.button3);
        game.buttons[3] = (Button) findViewById(R.id.button4);
        game.buttons[4] = (Button) findViewById(R.id.button5);
        game.buttons[5] = (Button) findViewById(R.id.button6);
        game.buttons[6] = (Button) findViewById(R.id.button7);
        game.buttons[7] = (Button) findViewById(R.id.button8);
        game.buttons[8] = (Button) findViewById(R.id.button9);
        game.buttons[9] = (Button) findViewById(R.id.button10);
        game.buttons[10] = (Button) findViewById(R.id.button11);
        game.buttons[11] = (Button) findViewById(R.id.button12);
        game.buttons[12] = (Button) findViewById(R.id.button13);
        game.buttons[13] = (Button) findViewById(R.id.button14);
        game.buttons[14] = (Button) findViewById(R.id.button15);
        game.buttons[15] = (Button) findViewById(R.id.button16);

        // kondisi awal

        initialize();
        game.message = (TextView) findViewById(R.id.textView_message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ulangi) {
            game.startNewGame();
        } else if (item.getItemId() == R.id.keluar) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Keluar dari aplikasi?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk keluar!")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        (dialog, id) -> {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        })

                .setNegativeButton("Tidak", (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void clickNewGame(View v)
    {
        game.startNewGame();
        findViewById(R.id.button_newGame).setVisibility(View.INVISIBLE);
    }

    public void clickSliderTile(View tile)
    {

        game.tryToMoveTile(tile);
    }

    public void initialize()
    {
        for (int i = 0; i < size; i++)
            game.buttons[i].setClickable(false);
    }
}
