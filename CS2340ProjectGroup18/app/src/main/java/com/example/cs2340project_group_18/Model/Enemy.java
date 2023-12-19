package com.example.cs2340project_group_18.Model;

//import android.graphics.BitmapFactory;
//import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;

//import com.example.cs2340project_group_18.ViewModel.GameScreen;
public class Enemy {
    private Bitmap sprite;
    private int x;
    private int y;
    private int size;
    private boolean isHit;

    public Enemy(Bitmap sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.size = size;
        this.isHit = false; // Set as not hit initially
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }


    public void updatePosition(int dx, int dy) {
        x += dx;
        y += dy;
    }
}