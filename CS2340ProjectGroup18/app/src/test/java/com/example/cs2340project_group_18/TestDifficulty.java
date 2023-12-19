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
public class TestDifficulty {
    @Test
    public void correctDifficultyFromConfigScreen() {
        if (ConfigScreen.getDifficulty() == "easy") {
            assertEquals("easy", (String) GameScreen.getDifficulty());
        } else if (ConfigScreen.getDifficulty() == "medium") {
            assertEquals("medium", (String) GameScreen.getDifficulty());
        } else if (ConfigScreen.getDifficulty() == "hard") {
            assertEquals("hard", (String) GameScreen.getDifficulty());
        }
    }
}