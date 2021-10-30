package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView playeronescore,playersecondscore,playerstatus;
    private Button[] buttons =new Button[9];
    private Button rst;
    private  int playeronescorecount,playersecondscorecount,rountcount;
    boolean activeplayer;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playeronescore=(TextView) findViewById(R.id.playeronescore);
        playersecondscore=(TextView) findViewById(R.id.playersecondscore);
        playerstatus=(TextView) findViewById(R.id.playerstatus);
        rst=(Button) findViewById(R.id.rst);
        for(int i=0; i<buttons.length;i++){
            String buttonId="btn_"+i;
            int resourceID=getResources().getIdentifier(buttonId,"id",getPackageName());
            buttons[i]=(Button)findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        rountcount=0;
        playeronescorecount=0;
        playersecondscorecount=0;
        activeplayer=true;



    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals((""))) {
            return;
        }
        String buttonId = v.getResources().getResourceEntryName(v.getId());
        int gamestatepointer = Integer.parseInt(buttonId.substring(buttonId.length() - 1, buttonId.length()));
        if (activeplayer) {
            ((Button) v).setText("X");
            gameState[gamestatepointer] = 0;
        } else {
            ((Button) v).setText("O");
            gameState[gamestatepointer] = 1;
        }
        rountcount++;
        if (checkwinner()) {
            if(activeplayer){
                playeronescorecount++;
                updatePlayerscore();
                Toast.makeText(this, "Player One Won", Toast.LENGTH_SHORT).show();
                playAgain();
            }else{ playersecondscorecount++;
                updatePlayerscore();
                Toast.makeText(this, "Player Second Won", Toast.LENGTH_SHORT).show();
                playAgain();

            }

        } else if (rountcount == 9) {
            Toast.makeText(this, "No winner ", Toast.LENGTH_SHORT).show();
        } else {
            activeplayer = !activeplayer;
        }
        if(playeronescorecount>playersecondscorecount){
            playerstatus.setText("Player One is Winning!");
        }else if(playersecondscorecount>playeronescorecount){
            playerstatus.setText("Player Two is Winning!");
        }else{
            playerstatus.setText("");
        }
        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playeronescorecount=0;
                playersecondscorecount=0;
                playerstatus.setText("");
                updatePlayerscore();
            }
        });
    }
    public boolean checkwinner(){
        boolean winnerResult=false;
        for(int[]winninPosion:winPositions){
            if (gameState[winninPosion[0]] == gameState[winninPosion[1]]&&
                    gameState[winninPosion[1]]==gameState[winninPosion[2]]&&
                    gameState[winninPosion[0]] !=2){
                winnerResult= true;
            }
        }
        return winnerResult;

    }
    public void updatePlayerscore(){
        playeronescore.setText(Integer.toString(playeronescorecount));
        playersecondscore.setText(Integer.toString(playersecondscorecount));
    }
    public void playAgain(){
        rountcount=0;
        activeplayer=true;
        for (int i=0;i<buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }
}