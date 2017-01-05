package com.demouser.scarne;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int userTotalScore = 0;
    int userTurnScore = 0;
    int compTotalScore = 0;
    int compTurnScore = 0;

    final String start = "Your turn score: 0; Computer total: 0; Your total: 0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView scores = (TextView) findViewById(R.id.score);
        scores.setText(start);

    }

    public int rollHelper(){
        Random rand = new Random();
        int n = rand.nextInt(6) + 1;
        return n;
    }

    public int roll(View view){
        int n = rollHelper();
        if(n==1){
            userTurnScore = 0;
            computerTurn();
        }
        else userTurnScore = n;
        return userTurnScore;
    }

    public boolean willComputerHold(int n){
        if(n==5 || n==6){
            return true; // yes hold, good roll!
        }
        else{ // otherwise randomly decide
            Random rand = new Random();
            int x = rand.nextInt(2) + 1;
            if(x==1) return false;
            else return true;
        }
    }


    public void hold(View view){
        userTotalScore += userTurnScore;
        computerTurn();
    }

    public void computerTurn(){
        Button roll = (Button) findViewById(R.id.roll);
        Button hold = (Button) findViewById(R.id.hold);
        roll.setEnabled(false);
        hold.setEnabled(false);
        boolean going = true;
        while(going){
            int n = rollHelper();
            if(n==1) going = false;
            else{
                compTurnScore = n;
                if(willComputerHold(compTurnScore)) {
                    going = false;
                    compTotalScore += compTurnScore;
                }
                // otherwise keep going
            }
        }
        roll.setEnabled(true);
        hold.setEnabled(true);
    }

    public boolean reset(View view){
        userTotalScore = 0;
        userTurnScore = 0;
        compTotalScore = 0;
        compTurnScore = 0;
        return true;
    }


}
