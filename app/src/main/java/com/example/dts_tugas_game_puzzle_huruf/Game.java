package com.example.dts_tugas_game_puzzle_huruf;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game {

    public Button[] buttons;
    public TextView message;
    private final int[] positions = new int[16];
    private final int size = 16;
    private int buttonClickedIndex = 0;
    private int emptyLocationIndex = 0;

    Game(Button[] inButtons, TextView inTextView)
    {
        buttons = inButtons;
        message = inTextView;

        for (int i = 0; i < size; i++)
        {
            positions[i] = i + 1;
        }
    }


    // Memulai game baru, akan melakukan acak untuk setiap button

    public void startNewGame()
    {
        shuffleAllTiles();

        // set button agar bisa diclick
        for (int i = 0; i < size; i++)
            buttons[i].setClickable(true);

        // menghapus pesan awal game
        message.setText("");
    }


    // Mengacak button/tiles


    private void shuffleAllTiles()
    {
        createListOfRandomTilePositions();

        // mengisi text dari button
        for (int i = 0; i < size; i++)
        {
            // reset button visibility
            buttons[i].setVisibility(View.VISIBLE);
            buttons[i].setText(String.format("%s", (char)(positions[i] + 64 )));
            if (positions[i] == 16) // diubah dari 9
                buttons[i].setVisibility(View.INVISIBLE);
        }
    }


    // mengecek jika ada button/tiles yang diklik

    public void tryToMoveTile(View tile)
    {
        for (int i = 0; i < size; i++)
        {
            if (tile.getId() == buttons[i].getId())
            {
                buttonClickedIndex = i;
                break;
            }
        }

        // Mengecek apakah button/tile tersebut bisa digeser?

        checkIfTileCanMove();

        // jika ada ruang kosong maka button/tiles dapat digeser
        if (emptyLocationIndex != -1)
        {
            // jika button/tile bisa digeser maka jalankan fungsi swapTwoTiles

            swapTwoTiles();
        }


        // Setelah menggeser button/tiles maka dilakukan cek apakah sudah menang?

        checkForWin();
    }

    private void checkForWin()
    {
        boolean win = true;

        // mengecek urutan tiles/button
        for (int i = 0; i < size; i++)
        {
            // jika ada tiles/button yang tidak urut maka tidak menang
            if (positions[i] != i + 1)
            {
                win = false;
                break;
            }
        }

        if (win)
        {
            gameOver();
        }
    }

    private void gameOver()
    {
        // set tiles/button agar tidak bisa diklik
        for (int i = 0; i < size; i++)
            buttons[i].setClickable(false);

        // pemberitahuan jika menang
        Toast.makeText(message.getContext(), "YOU WINNN", Toast.LENGTH_SHORT).show();
    }


    // fungsi untuk mengecek apakah button/tiles bisa bergeser

    private void checkIfTileCanMove()
    {
        // identifikasi lokasi geser dari button/tiles
        int[] locationsToCheck = new int[4];
        locationsToCheck[0] = buttonClickedIndex - 4; // atas
        locationsToCheck[1] = buttonClickedIndex + 4; // bawah
        locationsToCheck[2] = buttonClickedIndex - 1; // kiri
        locationsToCheck[3] = buttonClickedIndex + 1; // kanan

        if (buttonClickedIndex == 3 || buttonClickedIndex == 7 || buttonClickedIndex == 11) {
            // khusus untuk button idx 3,7, dan 11 ada kondisi khusus
            locationsToCheck[3] = -1; // jika tiles bergerak ke kanan maka false
        }

        for (int i = 0; i < 4; i++)
        {
            if (locationsToCheck[i] >= 0 && locationsToCheck[i] < 16) // diubah dari 9
            {
                if (!buttons[locationsToCheck[i]].isShown())
                {
                    emptyLocationIndex = locationsToCheck[i];
                    return;
                }
            }
        }
        emptyLocationIndex = -1;
    }


    // fungsi untuk menukar 2 button/tiles

    private void swapTwoTiles()
    {
        // buat tombol sekarang tidak terlihat
        buttons[buttonClickedIndex].setVisibility(View.INVISIBLE);

        // set text dari empty location dengan text dari button yang diklik
        buttons[emptyLocationIndex].setText(buttons[buttonClickedIndex].getText());

        // set button baru ke terlihat
        buttons[emptyLocationIndex].setVisibility(View.VISIBLE);

        // menukar posisi tiles
        int temp = positions[emptyLocationIndex];
        positions[emptyLocationIndex] = positions[buttonClickedIndex];
        positions[buttonClickedIndex] = temp;
    }


    // membuat tiles position

    private void createListOfRandomTilePositions()
    {
        for (int i = size - 1; i >= 1; --i)
        {
            int j = (int)(Math.random() * size);
            int temp = positions[j];
            positions[j] = positions[i];
            positions[i] = temp;
        }
    }
}

