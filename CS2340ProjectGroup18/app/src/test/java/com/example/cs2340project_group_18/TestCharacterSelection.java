package com.example.cs2340project_group_18;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340project_group_18.ViewModel.ConfigScreen;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestCharacterSelection {
    @Test
    public void character1Shown() {
        ConfigScreen.setCharacterName("character1");
        assertEquals("character1", ConfigScreen.getCharacterName());
    }
    @Test
    public void character2Shown() {
        ConfigScreen.setCharacterName("character2");
        assertEquals("character2", ConfigScreen.getCharacterName());
    }
    @Test
    public void character3Shown() {
        ConfigScreen.setCharacterName("character3");
        assertEquals("character3", ConfigScreen.getCharacterName());
    }
}
