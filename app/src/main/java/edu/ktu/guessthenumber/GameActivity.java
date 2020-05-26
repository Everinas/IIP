package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {


    private static final String PREFERENCES_FILE_NAME = "SettingsPref";
    private int minNumber = 0;
    private int maxNumber = 100;

    private int randomNumber;
    private int guessedNumber = 0;
    private int maxTurns = 7;
    private int currentTurn;

    private int result = 0;
    private TextView numberRangeText;
    private TextView resultText;
    private TextView turnsText;
    private EditText numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        int dif = prefs.getInt("difficulty",1);
        if (dif == 0){
            maxNumber = 10;
        }
        numberRangeText = findViewById(R.id.gameRange);
        resultText = findViewById(R.id.gameResult);
        turnsText = findViewById(R.id.gameTurns);

        updateText();



        Random random = new Random();
        randomNumber = random.nextInt(maxNumber-minNumber)+ minNumber;


    }

    private void updateText(){

        resultText.setText("");
        if (result == -1) {
            resultText.setText(String.format(getResources().getString(R.string.result_format),guessedNumber , getResources().getString(R.string.result_high)));

        }
        else if (result == 1){
            resultText.setText(String.format(getResources().getString(R.string.result_format), guessedNumber , getResources().getString(R.string.result_low)));
        }
        else if (result == 2){
            resultText.setText(String.format(getResources().getString(R.string.outofrange_format), guessedNumber ));
        }

        numberRangeText.setText(String.format(getResources().getString(R.string.number_range_format), minNumber,maxNumber));
        //resultText.setText("");
        turnsText.setText(String.format(getResources().getString(R.string.turns_format), currentTurn,maxTurns));
    }

    public void guessClick(View view){


        currentTurn++;
        numberField = findViewById(R.id.game_entry);
        guessedNumber = Integer.parseInt(numberField.getText().toString());

        if (guessedNumber > maxNumber){
            result = 2;
        }
        else if (randomNumber > guessedNumber){
            result = 1;
        }
        else if(randomNumber < guessedNumber){
            result = -1;
        }
        else if (randomNumber == guessedNumber){
            result = 0;
        }

        if(currentTurn >= maxTurns && result != 0){

            //lose
            Intent intent = new Intent(this, EndActivity.class);
            intent.putExtra("guessedNumber", guessedNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("win", false);
            startActivity(intent);
            finish();
        }
        else if (result == 0){
            //win
            Intent intent = new Intent(this, EndActivity.class);
            intent.putExtra("guessedNumber", guessedNumber);
            intent.putExtra("randomNumber", randomNumber);
            intent.putExtra("win", true);
            startActivity(intent);
            finish();
        }
        updateText();


    }


}
