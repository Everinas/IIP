package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {


    myDbAdapter helper;

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefs_edit = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent intent = getIntent();
        boolean win = intent.getBooleanExtra("win",false);
        TextView result = findViewById(R.id.result_text);
        int dif = prefs.getInt("difficulty", 0);
        String res = "";
        String dificulty = "";
        if(win){

            result.setText("You are the winner");
            res = "Win";
            if (dif == 0) {
                dificulty = "Easy";
                int wins = prefs.getInt("easy_win_count", 0);
                wins++;
                prefs_edit.putInt("easy_win_count",wins);
            }
            else{
                dificulty = "Impossible";
                int wins = prefs.getInt("imp_win_count", 0);
                wins++;
                prefs_edit.putInt("imp_win_count",wins);
            }


        }
        else{

            result.setText("You are the loser");
            res = "Loss";
            if (dif == 0) {
                dificulty = "Easy";
                int loss = prefs.getInt("easy_loss_count", 0);
                loss++;
                prefs_edit.putInt("easy_loss_count",loss);
            }
            else{
                dificulty = "Impossible";
                int loss = prefs.getInt("imp_loss_count", 0);
                loss++;
                prefs_edit.putInt("imp_loss_count",loss);
            }

        }

        String name = prefs.getString("playerName", "Name");

        add(name,dificulty,res);
        prefs_edit.apply();

    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this,data);
    }
    public void add(String name,String dif, String res){

        long id = helper.insertData(name,dif,res);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Error while saving");
        } else {
            Message.message(getApplicationContext(), "Result saved");
        }
    }

    public void startClick(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
    public void ResultClick(View view)
    {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
        finish();
    }

}




