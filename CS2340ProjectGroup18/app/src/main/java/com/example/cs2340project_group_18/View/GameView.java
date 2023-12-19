package com.example.cs2340project_group_18.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.os.Handler;

import androidx.annotation.Nullable;

//import com.example.cs2340project_group_18.Model.Enemy;
//import com.example.cs2340project_group_18.Model.Enemy;
import com.example.cs2340project_group_18.Model.HealthPowerUp;
import com.example.cs2340project_group_18.Model.EnemyCollisionSubject;
import com.example.cs2340project_group_18.Model.Player;
import com.example.cs2340project_group_18.Model.ScorePowerUp;
//import com.example.cs2340project_group_18.Model.SpeedPowerUp;
import com.example.cs2340project_group_18.Model.InvincibilityPowerUp;
import com.example.cs2340project_group_18.R;
//import com.example.cs2340project_group_18.ViewModel.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    private static Bitmap tile;
    private static Bitmap door;
    private static Bitmap playerBitmap;
    private static Bitmap sand;
    private static Bitmap dirt;
    private static Bitmap grass;
    private static Bitmap enemyA;
    private static Bitmap enemyB;
    private static Bitmap enemyC;
    private static Bitmap enemyD;
    private static Bitmap fireball;
    private static boolean fActive;
    private static int fX;
    private static int fY;
    private static final int FIREBALL_COOLDOWN = 500; // Cooldown in milliseconds
    private long lastFireballTime = 0;
    private Runnable fireballUpdater;
    private static final int FIREBALL_UPDATE_INTERVAL = 50; // Update fireball every 50 milliseconds
    private static GameView gameView;
    private static Player player;
    private static Bitmap health;
    private static Bitmap score;
    private static Bitmap invincibility;
    private List<Bitmap> activeEnemies = new ArrayList<>();
    private static boolean enemyAHit = false;
    private static boolean enemyBHit = false;
    private static boolean enemyCHit = false;
    private static boolean enemyDHit = false;

    private static String lastKeyClicked;
    private static String keyWhenClicked;
    private Context context;
    private static int dungeonNumber;
    private static int screenWidth = 1050;
    private static int screenHeight = 1450;

    private Handler handler;
    private Runnable enemyUpdater;
    private static final int ENEMY_UPDATE_INTERVAL = 100; // Update enemies every 100 milliseconds
    private static final Paint PAINT = new Paint();

    private static int enemyAX;
    private static int enemyAY;
    private static int enemyBX;
    private static int enemyBY;
    private static int enemyCX;
    private static int enemyCY;
    private static int enemyDX;
    private static int enemyDY;
    private static int healthPowerUpX = 600;
    private static int healthPowerUpY = 600;
    private static int scorePowerUpX = 200;
    private static int scorePowerUpY = 200;
    private static int invincibilityPowerUpX = 800;
    private static int invincibilityPowerUpY = 1300;

    private boolean isAlive = true;

    // Initialize your enemy positions and speeds as needed
    private static int enemyASpeed = 5;
    private static int enemyBSpeed = 8;
    private static int enemyCSpeed = 10;
    private static int enemyDSpeed = 7;
    private static int damageBuffer;

    public GameView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        initializeDirt();
        initializeGrass();
        initializeSand();
        dungeonNumber = 1;
        damageBuffer = 0;
        fActive = true;
        player = Player.getInstance();
        playerBitmap = ((BitmapDrawable) player.getPlayerImage()).getBitmap();
        tile = BitmapFactory.decodeResource(context.getResources(), R.drawable.dirt_dungeon_map);
        door = BitmapFactory.decodeResource(context.getResources(), R.drawable.door);
        enemyA = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bat), 0.07f);
        enemyB = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.skeleton), 0.085f);
        enemyC = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.slime), 0.095f);
        enemyD = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.thug), 0.07f);

        fireball = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.fireball), 0.05f);

        health = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.health), 0.08f);
        score = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.score), 0.2f);
        invincibility = scaleBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.invincibility), 0.3f);

        activeEnemies.add(enemyA);
        activeEnemies.add(enemyB);
        activeEnemies.add(enemyC);
        activeEnemies.add(enemyD);

        handler = new Handler();
        enemyUpdater = new Runnable() {
            @Override
            public void run() {
                if (damageBuffer != 0) {
                    damageBuffer--;
                }
                // Update enemy positions here based on their movement logic
                updateEnemyPositions();

                // Redraw the view to reflect the updated positions
                invalidate();

                // Schedule the next update
                handler.postDelayed(this, ENEMY_UPDATE_INTERVAL);
            }
        };
        handler.postDelayed(enemyUpdater, ENEMY_UPDATE_INTERVAL); // Start the enemy update loop
    }
    private Bitmap scaleBitmap(Bitmap originalBitmap, float scaleFactor) {
        int newWidth = (int) (originalBitmap.getWidth() * scaleFactor);
        int newHeight = (int) (originalBitmap.getHeight() * scaleFactor);
        return Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, false);
    }

    // Implement the updateEnemyPositions method to move enemies based on their logic
    private void updateEnemyPositions() {
        // Update enemyA position based on the movement logic (diagonal)
        enemyAX += enemyASpeed; // Move horizontally
        enemyAY += enemyASpeed; // Move vertically

        // Implement boundary checks to keep enemyA within the screen
        if (enemyAX < 0 || enemyAX > screenWidth - enemyA.getWidth()) {
            // Reverse the horizontal direction to make it bounce
            enemyASpeed = -enemyASpeed;
            enemyAX += enemyASpeed; // Correct the position
        }

        if (enemyAY < 0 || enemyAY > screenHeight - enemyA.getHeight()) {
            // Reverse the vertical direction to make it bounce
            enemyASpeed = -enemyASpeed;
            enemyAY += enemyASpeed; // Correct the position
        }

        // Update enemyB position based on the movement logic
        enemyBX += enemyBSpeed; // Move horizontally

        // Implement boundary checks to keep enemyB within the screen
        if (enemyBX < 0 || enemyBX > screenWidth - enemyB.getWidth()) {
            // Reverse the horizontal direction to make it bounce
            enemyBSpeed = -enemyBSpeed;
            enemyBX += enemyBSpeed; // Correct the position
        }

        // Update enemyC position based on the movement logic (diagonal)
        enemyCX += enemyCSpeed; // Move horizontally
        enemyCY += enemyCSpeed; // Move vertically

        // Implement boundary checks to keep enemyC within the screen
        if (enemyCX < 0 || enemyCX > screenWidth - enemyC.getWidth()) {
            // Reverse the horizontal direction to make it bounce
            enemyCSpeed = -enemyCSpeed;
            enemyCX += enemyCSpeed; // Correct the position
        }

        if (enemyCY < 0 || enemyCY > screenHeight - enemyC.getHeight()) {
            // Reverse the vertical direction to make it bounce
            enemyCSpeed = -enemyCSpeed;
            enemyCY += enemyCSpeed; // Correct the position
        }

        // Update enemyD position based on the movement logic
        enemyDY += enemyDSpeed; // Move vertically

        // Implement boundary checks to keep enemyD within the screen
        if (enemyDY < 0 || enemyDY > screenHeight - enemyD.getHeight()) {
            // Reverse the vertical direction to make it bounce
            enemyDSpeed = -enemyDSpeed;
            enemyDY += enemyDSpeed; // Correct the position
        }
        enemyCollisions();
    }

    public void shootFireball(String direction) {
        Log.d("Fireball", "Preparing to shoot fireball"); // Add log statement
        long currentTime = System.currentTimeMillis();

        // Apply cooldown before allowing another fireball
        if (currentTime - lastFireballTime < FIREBALL_COOLDOWN) {
            return;
        }

        lastFireballTime = currentTime;

        GameView.setfX(player.getX());
        GameView.setfY(player.getY());
        keyWhenClicked = direction;
        GameView.setFActive(true);
        gameView = findViewById(R.id.gameView);
        startFireballUpdateLoop(direction); // Start the fireball update loop when triggered
        gameView.invalidate();
    }

    private void startFireballUpdateLoop(String direction) {
        Log.d("Fireball", "Starting fireball update loop"); // Add log statement

        fireballUpdater = new Runnable() {
            @Override
            public void run() {
                updateFireballPosition(direction);
                invalidate(); // Redraw the view to reflect the updated fireball position
                handler.postDelayed(this, FIREBALL_UPDATE_INTERVAL); // Schedule the next update
            }
        };
        handler.postDelayed(fireballUpdater,
                FIREBALL_UPDATE_INTERVAL); // Start the fireball update loop
    }

    private void updateFireballPosition(String direction) {
        Log.d("Fireball", "Updating fireball position"); // Add log statement

        if (fActive) {
            switch (direction) {
            case "UP":
                fY -= 9;
                break;
            case "DOWN":
                fY += 9;
                break;
            case "LEFT":
                fX -= 9;
                break;
            case "RIGHT":
                fX += 9;
                break;
            default :
                break;
            }
            // Check for collisions with walls or enemies
            if (fireballCollidesWithWall()) {
                fActive = false; // Deactivate fireball due to collision with a wall
            }
            // Check for collisions with enemies
            checkEnemyCollisions();
        }
    }

    // Within your collision detection logic:
    private void checkEnemyCollisions() {
        if (fActive) {
            if (checkCollisionWithEnemy(enemyAX, enemyAY, enemyA.getWidth(), enemyA.getHeight())) {
                if (!enemyAHit) {
                    player.setScore(player.getScore() + 10);
                    enemyAHit = true; // Mark enemy A as hit
                }
            }
            if (checkCollisionWithEnemy(enemyBX, enemyBY, enemyB.getWidth(), enemyB.getHeight())) {
                if (!enemyBHit) {
                    player.setScore(player.getScore() + 15);
                    enemyBHit = true; // Mark enemy B as hit
                }
            }
            if (checkCollisionWithEnemy(enemyCX, enemyCY, enemyC.getWidth(), enemyC.getHeight())) {
                if (!enemyCHit) {
                    player.setScore(player.getScore() + 20);
                    enemyCHit = true; // Mark enemy C as hit
                }
            }
            if (checkCollisionWithEnemy(enemyDX, enemyDY, enemyD.getWidth(), enemyD.getHeight())) {
                if (!enemyDHit) {
                    player.setScore(player.getScore() + 10);
                    enemyDHit = true; // Mark enemy D as hit
                }
            }
            // Check collisions with other enemies similarly...
        }
    }

    private boolean checkCollisionWithEnemy(int enemyX, int enemyY,
                                            int enemyWidth, int enemyHeight) {
        // Calculate collision between fireball and enemy
        // Determine if the fireball (fX, fY) collides with the enemy's position and size
        // Return true if collision occurs, false otherwise
        return (fX < enemyX + enemyWidth
                &&
                fX + fireball.getWidth() > enemyX
                &&
                fY < enemyY + enemyHeight
                &&
                fY + fireball.getHeight() > enemyY);
    }

    private boolean fireballCollidesWithWall() {
        // Check collision with walls based on the fireball position (fX, fY)
        // Implement logic to detect collision with walls
        // Return true if collision occurs, false otherwise
        if (fX < 0 || fX > screenWidth || fY < 0 || fY > screenHeight) {
            return true; // Collision detected with wall boundaries
        }
        Log.d("Collision", "Fireball collided with wall");
        return false;
    }

    public void enemyCollisions() {
        if (dungeonNumber == 1 && damageBuffer == 0) {
            EnemyCollisionSubject.collision(player.getX(), player.getY(), 50,
                    enemyAX, enemyAY, enemyA.getWidth(), enemyA.getHeight());
            EnemyCollisionSubject.collision(player.getX(), player.getY(), 50,
                    enemyBX, enemyBY, enemyB.getWidth(), enemyB.getHeight());
        } else if (dungeonNumber == 2 && damageBuffer == 0) {
            EnemyCollisionSubject.collision(player.getX(), player.getY(), 50,
                    enemyCX, enemyCY, enemyC.getWidth(), enemyC.getHeight());
            EnemyCollisionSubject.collision(player.getX(), player.getY(), 50,
                    enemyBX, enemyBY, enemyB.getWidth(), enemyB.getHeight());
        } else if (dungeonNumber == 3 && damageBuffer == 0) {
            EnemyCollisionSubject.collision(player.getX(), player.getY(), 50,
                    enemyCX, enemyCY, enemyC.getWidth(), enemyC.getHeight());
            EnemyCollisionSubject.collision(player.getX(), player.getY(), 50,
                    enemyDX, enemyDY, enemyD.getWidth(), enemyD.getHeight());
        }
    }
    private static boolean healthPowerUpExists = true; // Example: Health Power-Up present
    private static boolean scorePowerUpExists = true; // Example: Score Power-Up present
    private static boolean invincibilityPowerUpExists = true; // Invincibility Power-Up present
    // Draw the power-up on the canvas
    private void drawPowerUp(Canvas canvas) {
        if (healthPowerUpExists) {
            // Draw a health power-up icon at a specific position on the canvas
            canvas.drawBitmap(health, healthPowerUpX, healthPowerUpY, PAINT);
        }
        if (scorePowerUpExists) {
            // Draw a score power-up icon at a specific position on the canvas
            canvas.drawBitmap(score, scorePowerUpX, scorePowerUpY, PAINT);
        }
        if (invincibilityPowerUpExists) {
            // Draw a invincibility power-up icon at a specific position on the canvas
            canvas.drawBitmap(invincibility, invincibilityPowerUpX, invincibilityPowerUpY, PAINT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(tile, 0, 0, PAINT);
        if (dungeonNumber == 1) {
            canvas.drawBitmap(door, 300, 1000, PAINT);
            if (!enemyAHit) {
                canvas.drawBitmap(enemyA, enemyAX, enemyAY, PAINT);
            }
            if (!enemyBHit) {
                canvas.drawBitmap(enemyB, enemyBX, enemyBY, PAINT);
            }
        } else if (dungeonNumber == 2) {
            canvas.drawBitmap(door, 814, 1000, PAINT);
            if (!enemyBHit) {
                canvas.drawBitmap(enemyB, enemyBX, enemyBY, PAINT);
            }
            if (!enemyCHit) {
                canvas.drawBitmap(enemyC, enemyCX, enemyCY, PAINT);
            }
        } else {
            canvas.drawBitmap(door, 1000, 1000, PAINT);
            if (!enemyCHit) {
                canvas.drawBitmap(enemyC, enemyCX, enemyCY, PAINT);
            }
            if (!enemyDHit) {
                canvas.drawBitmap(enemyD, enemyDX, enemyDY, PAINT);
            }
        }
        if (fActive) {
            Log.d("Fireball", "Drawing fireball at position: "
                    + fX + ", " + fY); // Add log statement
            canvas.drawBitmap(fireball, fX, fY, PAINT); // Draw fireball at updated position
        }
        playerBitmap = ((BitmapDrawable) player.getPlayerImage()).getBitmap();
        canvas.drawBitmap(playerBitmap, player.getX(), player.getY(), PAINT);
        drawPowerUp(canvas);
        // Check for collisions between player and power-ups after drawing
        checkCollisionsWithPowerUps();
    }


    // Handle collision detection between player and power-ups
    public void checkCollisionsWithPowerUps() {
        if (healthPowerUpExists && checkCollision(player.getX(), player.getY(), healthPowerUpX,
                healthPowerUpY, health.getWidth(), health.getHeight())) {
            // Player has collided with the health power-up
            player.collectHealthPowerUp(new HealthPowerUp(5));
            healthPowerUpExists = false; // Health power-up collected, remove it from the room
        }
        if (scorePowerUpExists && checkCollision(player.getX(), player.getY(), scorePowerUpX,
                scorePowerUpY, score.getWidth(), score.getHeight())) {
            Log.d("Collision", "Player collided with score power-up");
            player.collectScorePowerUp(new ScorePowerUp(10)); // Change 10 to your score increase
            Log.d("Power-up", "Score power-up collected");
            scorePowerUpExists = false; // Score power-up collected, remove it from the room
        }
        if (invincibilityPowerUpExists && checkCollision(player.getX(),
                player.getY(), invincibilityPowerUpX,
                invincibilityPowerUpY, invincibility.getWidth(), invincibility.getHeight())) {
            Log.d("Collision", "Player collided with invincibility power-up");
            player.collectInvincibilityPowerUp(
                    new InvincibilityPowerUp(10)); // Change 10 seconds to your invincibility
            Log.d("Power-up", "Invincibility power-up collected");
            invincibilityPowerUpExists = false; // Invincibility power-up collected remove from room
        }

        // Check collisions with other power-ups similarly...
    }

    // Helper method to check collision between player and power-up
    private boolean checkCollision(int playerX, int playerY, int powerUpX,
                                   int powerUpY, int powerUpWidth, int powerUpHeight) {
        // Get the dimensions of the player and the power-up
        int playerWidth = playerBitmap.getWidth();
        int playerHeight = playerBitmap.getHeight();
        //int powerUpWidth = health.getWidth(); // Assuming health is the power-up image
        //int powerUpHeight = health.getHeight(); // Adjust if the power-up image differs

        // Calculate the boundaries of the player and the power-up
        int playerLeft = playerX;
        int playerRight = playerX + playerWidth;
        int playerTop = playerY;
        int playerBottom = playerY + playerHeight;

        int powerUpLeft = powerUpX;
        int powerUpRight = powerUpX + powerUpWidth;
        int powerUpTop = powerUpY;
        int powerUpBottom = powerUpY + powerUpHeight;

        // Check for collision between the player and the power-up
        return (playerRight >= powerUpLeft
                &&
                playerLeft <= powerUpRight
                &&
                playerBottom >= powerUpTop
                &&
                playerTop <= powerUpBottom);
    }
    public static void changeTile(int dungeonNum) {
        if (dungeonNum == 1) {
            tile = dirt;
            dungeonNumber = 1;
            resetEnemies(); // Reset enemy hit flags when moving to dungeon 1
            resetPowerUps();
        } else if (dungeonNum == 2) {
            tile = grass;
            dungeonNumber = 2;
            resetEnemies(); // Reset enemy hit flags when moving to dungeon 2
            resetPowerUps();
        } else {
            tile = sand;
            dungeonNumber = 3;
            resetEnemies(); // Reset enemy hit flags when moving to dungeon 3
            resetPowerUps();
        }
    }

    public static void resetEnemies() {
        // Reset hit flags for enemies
        enemyAHit = false;
        enemyBHit = false;
        enemyCHit = false;
        enemyDHit = false;
    }

    public static void resetPowerUps() {
        // Set the flags for power-ups to indicate they are available again
        healthPowerUpExists = true;
        scorePowerUpExists = true;
        invincibilityPowerUpExists = true;
    }

    private void initializeDirt() {
        dirt = BitmapFactory.decodeResource(context.getResources(), R.drawable.dirt_dungeon_map);
    }

    private void initializeSand() {
        sand = BitmapFactory.decodeResource(context.getResources(), R.drawable.sand_dungeon_map);
    }

    private void initializeGrass() {
        grass = BitmapFactory.decodeResource(context.getResources(), R.drawable.grass_dungeon_map);
    }

    public static void setFActive(boolean active) {
        fActive = active;
    }
    public boolean getFActive() {
        return fActive;
    }

    public static void setKeyWhenClicked(String key) {
        keyWhenClicked = key;
    }

    public static void setfX(int x) {
        fX = x;
    }
    public static void setfY(int y) {
        fY = y;
    }

    // Getter and setter for the 'isAlive' flag
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    // Add getter and setter methods for enemyA's position
    public int getEnemyAX() {
        return enemyAX;
    }

    public int getEnemyAY() {
        return enemyAY;
    }
    public int getEnemyBX() {
        return enemyBX;
    }

    public int getEnemyBY() {
        return enemyBY;
    }
    public int getEnemyCX() {
        return enemyCX;
    }

    public int getEnemyCY() {
        return enemyCY;
    }
    public int getEnemyDX() {
        return enemyDX;
    }

    public int getEnemyDY() {
        return enemyDY;
    }

    public void setEnemyAPosition(int x, int y) {
        enemyAX = x;
        enemyAY = y;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
    public Bitmap getEnemyA() {
        return enemyA;
    }

    public static void resetDamageBuffer() {
        damageBuffer = 25;
    }

}