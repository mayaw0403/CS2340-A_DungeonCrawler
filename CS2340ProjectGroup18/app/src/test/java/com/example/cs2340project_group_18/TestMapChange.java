package com.example.cs2340project_group_18;

import static org.junit.Assert.assertEquals;

import com.example.cs2340project_group_18.ViewModel.GameScreen;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestMapChange {
    @Test
    public void cycleMapCorrectly() {
        if (GameScreen.getDungeonNumber() == 1) {
            GameScreen.changeMap();
            assertEquals(2, GameScreen.getDungeonNumber());
        } else if (GameScreen.getDungeonNumber() == 2) {
            GameScreen.changeMap();
            assertEquals(3, GameScreen.getDungeonNumber());
        } else if (GameScreen.getDungeonNumber() == 3) {
            GameScreen.changeMap();
            assertEquals(1, GameScreen.getDungeonNumber());
        }
    }
}