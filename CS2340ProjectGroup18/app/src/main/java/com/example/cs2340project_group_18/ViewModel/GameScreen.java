package com.example.cs2340project_group_18.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import com.example.cs2340project_group_18.Model.Player;
import com.example.cs2340project_group_18.R;
import com.example.cs2340project_group_18.View.GameView;

import java.util.HashSet;
import java.util.Set;


public class GameScreen extends AppCompatActivity {
    private static GameView gameView;
    private static String difficulty;
    private static Player player;
    private static Integer health;
    private static boolean lost;
    private static int dungeonNumber;
    private static String lastKeyClicked;
    private static String keyWhenClicked;

    private static Set<Integer> visitedDoors = new HashSet<>(); // To keep track of visited doors

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        difficulty = ConfigScreen.getDifficulty();
        TextView tv = (TextView) findViewById(R.id.textView);
        TextView scoreText = (TextView) findViewById(R.id.scoreTextView);
        player = Player.getInstance();
        player.setY(500);
        player.setX(300);
        player.setScore(0);
        lost = false;
        setHealth();
        player.setPlayerImage();
        dungeonNumber = 1;
        tv.setText("Name: " + ConfigScreen.getName() + "\nDifficulty: "
                + difficulty
                + "\nHealth: " + health);

        new CountDownTimer(999999999, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                scoreText.setText("Score: " + player.getScore());
            }

            @Override
            public void onFinish() {
                start();
            }
        }.start();
        gameView = findViewById(R.id.gameView);
        gameView.invalidate();

        resetGameState();
    }

    /** @noinspection checkstyle:FallThrough, checkstyle:FallThrough */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int playerX = player.getX();
        int playerY = player.getY();
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_UP:
            movePlayer(0, -20); // Move up
            lastKeyClicked = "UP";
            return true;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            movePlayer(0, 20); // Move down
            lastKeyClicked = "DOWN";
            return true;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            movePlayer(-20, 0); // Move left
            lastKeyClicked = "LEFT";
            return true;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            movePlayer(20, 0); // Move right
            lastKeyClicked = "RIGHT";
            return true;
        case KeyEvent.KEYCODE_SPACE:
            Log.d("Fireball", "Spacebar pressed, preparing to shoot fireball"); // Add log statement
            GameView gameView = findViewById(R.id.gameView);
            if (gameView != null) {
                GameView.setfX(player.getX());
                GameView.setfY(player.getY());
                GameView.setFActive(true);
                GameView.setKeyWhenClicked(lastKeyClicked); // Set the direction for the fireball
                gameView.shootFireball(lastKeyClicked); // Call the shootFireball method
                gameView.invalidate();
            }
            return super.onKeyDown(keyCode, event);
        default:
            return super.onKeyDown(keyCode, event);
        }
        //return true;
    }
    private void movePlayer(int deltaX, int deltaY) {
        int newX = player.getX() + deltaX;
        int newY = player.getY() + deltaY;

        // Check for collisions or boundaries here and update the player's position accordingly.
        // Implement your collision and boundary checks

        if (wallCollisions(newX, newY)) {
            player.setX(newX);
            player.setY(newY);
        }
        gameView.invalidate(); // Redraw the view
        updateHealth();
        // Call changeDungeon when the player's position is updated
        boolean end = changeDungeon(newX, newY);
        if (end || lost) {
            openEndScreen();
        }
    }

    public static boolean wallCollisions(int x, int y) {
        if (dungeonNumber == 1) {
            if (x < 60 || x > 1200 || y < 70 || y > 1740) {
                return false;
            }
        } else if (dungeonNumber == 2) {
            if ((x < 900 && y < 660 && x > 320 && y > 500)
                    || (x > 340 && y > 680 && x < 880 && y < 1000)
                    ||   x < 30 || x > 1230 || y < 30 || y > 1800) {
                return false;
            }
        } else {
            if (x < 10 || x > 1230 || y < 20 || y > 1800
                    || (x > 1070 && y > 1610)
                    || (x > 990 && y > 100 && x < 1170 && y < 280)) {
                return false;
            }
        }
        return true;
    }

    public static boolean changeDungeon(int playerX, int playerY) {
        if (dungeonNumber == 1) {
            if (playerX < 434 && playerX > 230 && playerY > 936 && playerY < 1135) {
                if (player != null) {
                    player.setX(300);
                    player.setY(500);
                }
                visitedDoors.add(1); // Mark the door as visited
                changeMap();
                return visitedDoors.size() == 3; // Check if all doors have been visited
            }
        } else if (dungeonNumber == 2) {
            if (playerX < 434 + 514 && playerX > 230 + 514 && playerY > 936 && playerY < 1135) {
                if (player != null) {
                    player.setX(300);
                    player.setY(620);
                }
                visitedDoors.add(2); // Mark the door as visited
                changeMap();
                return visitedDoors.size() == 3; // Check if all doors have been visited
            }
        } else {
            if (playerX < 434 + 700 && playerX > 230 + 700 && playerY > 936 && playerY < 1135) {
                visitedDoors.add(3); // Mark the door as visited
                player.setScore(player.getScore() + 100);
                return visitedDoors.size() == 3; // Check if all doors have been visited
            }
        }
        return false;
    }
    public static void changeMap() {
        switch (dungeonNumber) {
        case 1:
            dungeonNumber = 2;
            GameView.setFActive(false);
            GameView.changeTile(2);
            break;
        case 2:
            dungeonNumber = 3;
            GameView.setFActive(false);
            GameView.changeTile(3);
            break;
        case 3:
            dungeonNumber = 1;
            GameView.setFActive(false);
            GameView.changeTile(1);
            break;
        default:
        }
        if (gameView != null) {
            gameView.invalidate();
        }
    }

    public void openEndScreen() {
        Log.d("Fireball", "Opening EndScreen"); // Add log statement
        Intent intent = new Intent(this, EndScreen.class);
        startActivity(intent);
    }

    public void setHealth() {
        switch (ConfigScreen.getDifficulty()) {
        case ("easy") :
            player.setHealth(10);
            health = 10;
            break;
        case ("medium") :
            player.setHealth(8);
            health = 8;
            break;
        case ("hard") :
            player.setHealth(6);
            health = 6;
            break;
        default :
            break;
        }
    }

    public void updateHealth() {
        health = player.getHealth();
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("Name: " + ConfigScreen.getName() + "\nDifficulty: "
                + difficulty
                + "\nHealth: " + health);
    }

    private void resetGameState() {
        // Reset player's position
        player.setX(200);
        player.setY(1200);

        // Clear visited doors
        visitedDoors.clear();

        // Reset dungeonNumber to 1
        dungeonNumber = 1;

        // Reset the score to its initial value
        player.setScore(0);

        // Reset enemies to their initial positions when starting a new game
        gameView.resetEnemies();

        // Reset power-ups to their default positions when starting a new game
        gameView.resetPowerUps();

    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static int getDungeonNumber() {
        return dungeonNumber;
    }
    public static void setDungeonNumber(int number) {
        dungeonNumber = number;
    }

    public static Integer getHealth() {
        return health;
    }
    public static Player getPlayer() {
        return player;
    }

    public static boolean getLost() {
        return lost;
    }
    public static String getLastKeyClicked() {
        return lastKeyClicked;
    }

    public static String getKeyWhenClicked() {
        return keyWhenClicked;
    }

    public static void setLost(boolean lost) {
        GameScreen.lost = lost;
    }

    private void someMethodInGameScreen(String direction) {
        // Call setKeyWhenClicked on the gameView instance
        gameView.setKeyWhenClicked(direction);
    }


}