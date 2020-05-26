package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFERENCES_FILE_NAME = "SettingsPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE);
       String playerName = prefs.getString("playerName", "Name");
       int playerAge = prefs.getInt("playerAge",1);
        int dif = prefs.getInt("difficulty", 0);
       boolean sound = prefs.getBoolean("sound", true);
       EditText nameField = findViewById(R.id.editName);
        EditText ageField = findViewById(R.id.editAge);
        Spinner spinner = findViewById(R.id.difficulty_spinner);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        nameField.setText(playerName);
        ageField.setText(Integer.toString(playerAge));
        spinner.setSelection(dif);
        soundSwitch.setChecked(sound);
    }

    public void saveSetting(View v){
        EditText playerNameField = findViewById(R.id.editName);
        EditText playerAgeFied = findViewById(R.id.editAge);
        Spinner spinner = findViewById(R.id.difficulty_spinner);
        Switch soundSwitch = findViewById(R.id.sound_switch);

        String playerName = playerNameField.getText().toString();
        int playerAge = Integer.parseInt(playerAgeFied.getText().toString());
        int difficulty = spinner.getSelectedItemPosition();
        boolean sound = soundSwitch.isChecked();

        SharedPreferences.Editor prefs = getSharedPreferences(PREFERENCES_FILE_NAME, MODE_PRIVATE).edit();

        prefs.putString("playerName",playerName);
        prefs.putInt("playerAge",playerAge);
        prefs.putInt("difficulty",difficulty);
        prefs.putBoolean("sound",sound);

        prefs.apply();
        finish();
    }
}
