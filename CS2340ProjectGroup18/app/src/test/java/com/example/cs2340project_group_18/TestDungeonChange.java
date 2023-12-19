package com.example.cs2340project_group_18;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340project_group_18.Model.Player;
import com.example.cs2340project_group_18.ViewModel.ConfigScreen;
import com.example.cs2340project_group_18.ViewModel.GameScreen;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestDungeonChange {
    @Test
    public void dungeonOneToTwo() {
        GameScreen.setDungeonNumber(1);
        GameScreen.changeDungeon(400, 1800);

        assertEquals(2, GameScreen.getDungeonNumber());
    }
    @Test
    public void dungeonTwoToThree() {
        GameScreen.setDungeonNumber(2);
        GameScreen.changeDungeon(800, 1800);
        assertEquals(3, GameScreen.getDungeonNumber());
    }
    /*
    @Test
    public void dungeonThreeToEnd() {
        GameScreen.setDungeonNumber(3);
        GameScreen.changeDungeon(1000, 1800);
        assertEquals(1, GameScreen.getDungeonNumber());
    }
     */
}