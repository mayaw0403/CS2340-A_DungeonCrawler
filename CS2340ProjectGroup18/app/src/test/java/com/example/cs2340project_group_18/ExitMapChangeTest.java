package com.example.cs2340project_group_18;

import com.example.cs2340project_group_18.View.GameView;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExitMapChangeTest {

    public String changeMapTest(int dungeonNumber) {
        switch (dungeonNumber) {
            case 1:
                dungeonNumber = 2;
                GameView.changeTile(dungeonNumber);
                return "grass";
            case 2:
                dungeonNumber = 3;
                GameView.changeTile(dungeonNumber);
                return "sand";
            case 3:
                dungeonNumber = 1;
                GameView.changeTile(dungeonNumber);
                return "dirt";
        }
        return null;
    }

    private String checkExit(int x, int y, int dungeonNumber) {
        if (dungeonNumber == 1) {
            if (x < 444 && x > 240 && y > 1736 && y < 1935) {
                return changeMapTest(dungeonNumber);
            }
        } else if (dungeonNumber == 2) {
            if (x < 444+514 && x > 240+514 && y > 1736 && y < 1935) {
                return changeMapTest(dungeonNumber);
            }
        } else {
            if (x < 444+700 && x > 240+700 && y > 1736 && y < 1935) {
                return changeMapTest(dungeonNumber);
            }
        }
        return null;
    }

    @Test
    public void testExitMapChange() {
        assertEquals("grass", checkExit(249, 1800, 1));
    }

    @Test
    public void testExitMapChange3() {
        assertEquals("dirt", checkExit(941, 1801, 3));
    }


}
