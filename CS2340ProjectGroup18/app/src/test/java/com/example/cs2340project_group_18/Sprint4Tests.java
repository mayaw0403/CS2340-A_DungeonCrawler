package com.example.cs2340project_group_18;
import android.graphics.Bitmap;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cs2340project_group_18.Model.Enemy;
import com.example.cs2340project_group_18.Model.EnemyTypeA;
import com.example.cs2340project_group_18.Model.EnemyTypeB;
import com.example.cs2340project_group_18.Model.EnemyTypeC;
import com.example.cs2340project_group_18.Model.EnemyTypeD;
import com.example.cs2340project_group_18.ViewModel.EnemyFactory;

public class Sprint4Tests {

    //Maya Williams
    @Test
    public void testCreateEnemyTypeAGetX() {
        Enemy enemy = EnemyFactory.createEnemy("A", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeA);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getX());
    }
    @Test
    public void testCreateEnemyTypeAGetY() {
        Enemy enemy = EnemyFactory.createEnemy("A", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeA);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getY());
    }

    //Amber Ephraim
    @Test
    public void testCreateEnemyTypeAGetSize() {
        Enemy enemy = EnemyFactory.createEnemy("A", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeA);
        assertNull(enemy.getSprite());
        assertEquals(10, enemy.getSize());
    }

    @Test
    public void testCreateEnemyTypeBGetX() {
        Enemy enemy = EnemyFactory.createEnemy("B", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeB);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getX());
    }

    //Gabriella Gonzalez
    @Test
    public void testCreateEnemyTypeBGetY() {
        Enemy enemy = EnemyFactory.createEnemy("B", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeB);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getY());
    }

    @Test
    public void testCreateEnemyTypeBGetSize() {
        Enemy enemy = EnemyFactory.createEnemy("B", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeB);
        assertNull(enemy.getSprite());
        assertEquals(40, enemy.getSize());
    }

    //Michelle Moise
    @Test
    public void testCreateEnemyTypeCGetX() {
        Enemy enemy = EnemyFactory.createEnemy("C", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeC);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getX());
    }
    @Test
    public void testCreateEnemyTypeCGetY() {
        Enemy enemy = EnemyFactory.createEnemy("C", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeC);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getY());
    }

    //Extra Tests
    @Test
    public void testCreateEnemyTypeCGetSize() {
        Enemy enemy = EnemyFactory.createEnemy("C", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeC);
        assertNull(enemy.getSprite());
        assertEquals(60, enemy.getSize());
    }

    @Test
    public void testCreateEnemyTypeDGetX() {
        Enemy enemy = EnemyFactory.createEnemy("D", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeD);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getX());
    }
    @Test
    public void testCreateEnemyTypeDGetY() {
        Enemy enemy = EnemyFactory.createEnemy("D", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeD);
        assertNull(enemy.getSprite());
        assertEquals(0, enemy.getY());
    }

    //Amber Ephraim
    @Test
    public void testCreateEnemyTypeDGetSize() {
        Enemy enemy = EnemyFactory.createEnemy("D", 0, 0, null, 1);

        assertTrue(enemy instanceof EnemyTypeD);
        assertNull(enemy.getSprite());
        assertEquals(100, enemy.getSize());
    }


}
