package com.example.cs2340project_group_18.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cs2340project_group_18.R;

//import org.w3c.dom.Text;

public class ConfigScreen extends AppCompatActivity {
    private TextView welcomeText;
    private static EditText nameInput;
    private static RadioButton easyButton;
    private static RadioButton mediumButton;
    private static RadioButton hardButton;
    private Button playButton;
    private static ImageButton character1;
    private static ImageButton character2;
    private static ImageButton character3;
    private static Drawable characterSelected;
    private boolean nameVerified;
    private static String name;
    private static String difficulty;
    private static boolean notAllWhiteSpace;
    private static String characterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);
        welcomeText = findViewById(R.id.welcomeText);
        nameInput = findViewById(R.id.nameInput);
        easyButton = findViewById(R.id.easyButton);
        mediumButton = findViewById(R.id.mediumButton);
        hardButton = findViewById(R.id.hardButton);
        playButton = findViewById(R.id.startButton);
        character1 = findViewById(R.id.char1Button);
        character2 = findViewById(R.id.char2Button);
        character3 = findViewById(R.id.char3Button);
        characterSelected = null;
        nameVerified = false;
        easyButton.setChecked(true);


        character1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCharacterName("character1");
                characterSelected = character1.getDrawable();
            }
        });

        character2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCharacterName("character2");
                characterSelected = character2.getDrawable();
            }
        });

        character3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCharacterName("character3");
                characterSelected = character3.getDrawable();

            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameVerified = validateName(nameInput.getText().toString());
                if (nameVerified && characterSelected != null) {
                    storeInputs();
                    System.out.println("Verified");
                    openGameScreen();
                }
            }
        });
    }
    public static void setCharacterName(String character) {
        characterName = character;
    }
    public static String getCharacterName() {
        return characterName;
    }
    public static boolean notNull(String name) {
        boolean nameLength = false;
        if (!(name.length() == 0 || name == null)) {
            nameLength = true;
        }
        return nameLength;
    }

    public static boolean notWhiteSpace(String name) {
        notAllWhiteSpace = false;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != ' ') {
                notAllWhiteSpace = true;
            }
        }
        return notAllWhiteSpace;
    }
    public static boolean validateName(String name) {

        if (notNull(name) && notWhiteSpace(name)) {
            return true;
        } else if (!notNull(name)) {
            nameInput.setError("this field is required");
        } else if (!notWhiteSpace(name)) {
            nameInput.setError("cannot be all whitespace");
        }

        return false;
    }

    public void openGameScreen() {
        Intent intent = new Intent(this, GameScreen.class);
        System.out.println(intent);
        startActivity(intent);
    }

    public static void storeInputs() {
        name = nameInput.getText().toString();
        if (easyButton.isChecked()) {
            difficulty = "easy";
        } else if (mediumButton.isChecked()) {
            difficulty = "medium";
        } else if (hardButton.isChecked()) {
            difficulty = "hard";
        }
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static String getName() {
        return name;
    }
    public static Drawable getCharacter(String character) {
        if (character == "character1") {
            return character1.getDrawable();
        }
        if (character == "character2") {
            return character2.getDrawable();
        }
        if (character == "character3") {
            return character3.getDrawable();
        }
        return null;
    }

    public static Drawable getCharacterSelected() {
        return characterSelected;
    }
}