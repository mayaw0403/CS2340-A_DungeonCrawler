package com.example.cs2340project_group_18;
import android.graphics.Bitmap;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cs2340project_group_18.Model.HealthPowerUp;
import com.example.cs2340project_group_18.Model.ScorePowerUp;
import com.example.cs2340project_group_18.Model.SpeedPowerUp;

public class Sprint5Tests {
    //Maya Williams
    @Test
    public void testHealthPowerUpApplication1() {
        // Create a HealthPowerUp with a health increase of 5
        HealthPowerUp healthPowerUp = new HealthPowerUp(5);

        // Check if the health increase retrieved matches the specified amount
        assertEquals(5, healthPowerUp.getHealthIncrease());
    }

    @Test
    public void testHealthPowerUpApplication2() {
        // Create a HealthPowerUp with a health increase of 10
        HealthPowerUp healthPowerUp = new HealthPowerUp(10);

        // Check if the health increase retrieved matches the specified amount
        assertEquals(10, healthPowerUp.getHealthIncrease());
    }

    //Amber Ephraim
    @Test
    public void testHealthPowerUpApplication3() {
        // Create a HealthPowerUp with a health increase of 15
        HealthPowerUp healthPowerUp = new HealthPowerUp(15);

        // Check if the health increase retrieved matches the specified amount
        assertEquals(15, healthPowerUp.getHealthIncrease());
    }

    @Test
    public void testScorePowerUpApplication1() {
        // Create a ScorePowerUp with a score increase of 5
        ScorePowerUp scorePowerUp = new ScorePowerUp(5);

        // Check if the health increase retrieved matches the specified amount
        assertEquals(5, scorePowerUp.getScoreIncrease());
    }

    //Gabriella Gonzalez
    @Test
    public void testScorePowerUpApplication2() {
        // Create a ScorePowerUp with a score increase of 10
        ScorePowerUp scorePowerUp = new ScorePowerUp(10);

        // Check if the health increase retrieved matches the specified amount
        assertEquals(10, scorePowerUp.getScoreIncrease());
    }
    @Test
    public void testScorePowerUpApplication3() {
        // Create a ScorePowerUp with a score increase of 15
        ScorePowerUp scorePowerUp = new ScorePowerUp(15);

        // Check if the health increase retrieved matches the specified amount
        assertEquals(15, scorePowerUp.getScoreIncrease());
    }

    //Michelle Moise
    @Test
    public void testSpeedPowerUpApplication1() {
        // Create a SpeedPowerUp with a speed increase of 5
        SpeedPowerUp speedPowerUp = new SpeedPowerUp(5);

        // Check if the speed increase retrieved matches the specified amount
        assertEquals(5, speedPowerUp.getSpeedIncrease());
    }

    @Test
    public void testSpeedPowerUpApplication2() {
        // Create a SpeedPowerUp with a speed increase of 10
        SpeedPowerUp speedPowerUp = new SpeedPowerUp(10);

        // Check if the speed increase retrieved matches the specified amount
        assertEquals(10, speedPowerUp.getSpeedIncrease());
    }
}
