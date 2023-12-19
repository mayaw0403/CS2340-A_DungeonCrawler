package com.example.cs2340project_group_18.Model;

public class Room {
    private boolean[][] walls;
    private int exitX;
    private int exitY;

    public Room(boolean[][] walls, int exitX, int exitY) {
        this.walls = walls;
        this.exitX = exitX;
        this.exitY = exitY;
    }

    public boolean isCollidingWithWalls(int x, int y) {
        if (x < 0 || x >= walls.length || y < 0 || y >= walls[0].length) {
            return true;
        }
        return walls[x][y];
    }

    public boolean hasReachedExit(int x, int y) {
        return x == exitX && y == exitY;
    }
}
