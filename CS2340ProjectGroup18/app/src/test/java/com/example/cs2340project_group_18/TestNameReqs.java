package com.example.cs2340project_group_18;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340project_group_18.ViewModel.ConfigScreen;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestNameReqs {
    @Test
    public void whitespaceDetection() {
        String name = "   ";
        assertEquals(ConfigScreen.notWhiteSpace(name), false);
    }

    @Test
    public void isEmptyDetection() {
        String name = "";
        assertEquals(ConfigScreen.notNull(name), false);
    }

    @Test
    public void isValid() {
        String name = "Gaby";
        assertEquals((ConfigScreen.notNull(name) && ConfigScreen.notWhiteSpace(name)), true);
    }
}