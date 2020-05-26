package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    myDbAdapter helper;
    MyRecyclerViewAdapter adapter;
    private static final String PREFERENCES_FILE_NAME = "SettingsPref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helper = new myDbAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        String name = prefs.getString("playerName","");
        int win_easy = helper.easyWinCount(name);
        int win_imp = helper.impWinCount(name);
        int loss_easy = helper.easyLossCount(name);
        int loss_imp = helper.impLossCount(name);
        TextView win_easy_txt = findViewById(R.id.easy_win_res);
        TextView win_imp_txt = findViewById(R.id.imp_win_res);
        TextView loss_easy_txt = findViewById(R.id.easy_loss_res);
        TextView loss_imp_txt = findViewById(R.id.imp_loss_res);

        win_easy_txt.setText(String.format(getResources().getString(R.string.win_format),win_easy));
        win_imp_txt.setText(String.format(getResources().getString(R.string.win_format),win_imp));
        loss_imp_txt.setText(String.format(getResources().getString(R.string.loss_format),loss_imp));
        loss_easy_txt.setText(String.format(getResources().getString(R.string.loss_format),loss_easy));


        ArrayList<GameClass> games = helper.getGames();


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, games);
        // adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }



    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this,data);

    }
    public void resultsMainClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void delete( View view)
    {
        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        String name = prefs.getString("playerName","");
        int a= helper.delete(name);
        if(a<=0) {
            Message.message(getApplicationContext(),"Unsuccessful");
        }
        else {
            Message.message(this, "DELETED");
        }
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }
    public void deleteAll( View view)
    {

        int a= helper.deleteAll();
        if(a<=0) {
            Message.message(getApplicationContext(),"Unsuccessful");
        }
        else {
            Message.message(this, "DELETED");
        }
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }

}
