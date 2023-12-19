package com.example.cs2340project_group_18.ViewModel;

import android.graphics.Bitmap;

import com.example.cs2340project_group_18.Model.Enemy;
import com.example.cs2340project_group_18.Model.EnemyTypeA;
import com.example.cs2340project_group_18.Model.EnemyTypeB;
import com.example.cs2340project_group_18.Model.EnemyTypeC;
import com.example.cs2340project_group_18.Model.EnemyTypeD;

public class EnemyFactory {
    public static Enemy createEnemy(String enemyType, int x, int y,
                                    Bitmap spriteFilePath, int size) {
        switch (enemyType) {
        case "A":
            return new EnemyTypeA(spriteFilePath, x, y);
        case "B":
            return new EnemyTypeB(spriteFilePath, x, y);
        case "C":
            return new EnemyTypeC(spriteFilePath, x, y);
        case "D":
            return new EnemyTypeD(spriteFilePath, x, y);
        default:
            throw new IllegalArgumentException("Invalid enemy type: " + enemyType);
        }
    }
}
