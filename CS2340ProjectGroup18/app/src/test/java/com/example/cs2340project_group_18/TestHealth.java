package com.example.cs2340project_group_18;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340project_group_18.ViewModel.ConfigScreen;
import com.example.cs2340project_group_18.ViewModel.GameScreen;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestHealth {
    @Test
    public void health_isCorrect() {
        if(ConfigScreen.getDifficulty() == "easy") {
            assertEquals((int) GameScreen.getHealth(), 7);
        } else if (ConfigScreen.getDifficulty() == "medium") {
            assertEquals((int) GameScreen.getHealth(), 5);
        } else if (ConfigScreen.getDifficulty() == "hard") {
            assertEquals((int) GameScreen.getHealth(), 3);
        }
    }
}