package com.example.cs2340project_group_18;

import com.example.cs2340project_group_18.View.BackgroundMusic;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MusicTest {
    @Test
    public void testGetMusic() {
        BackgroundMusic music = new BackgroundMusic();
        assertNull(music.getMusic());
    }

    @Test
    public void testSetMusic() {
        BackgroundMusic music = new BackgroundMusic();
        music.setMusic(null);
        assertNull(music.getMusic());
    }

}
