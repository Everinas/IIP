package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    //int m_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGameBtn = findViewById(R.id.start_game_btn);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClick(v);
            }
        }) ;

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
        String playerName = "Hello " + prefs.getString("playerName", "Guess the number");
        TextView nameField = findViewById(R.id.textName);
        nameField.setText(playerName);


    }

    public void startClick(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
    public void ResultClick(View view)
    {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);

    }
    public void AboutClick(View view)
    {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);

    }
    public void SettingClick(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }
}
