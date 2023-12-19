package com.example.cs2340project_group_18.Model;

public class MoveLeftStrategy implements MovementStrategy {
    @Override
    public void move(Player player) {
        int newX = player.getX() - 1;
        int newY = player.getY();


        if (player.isWithinScreenBoundaries(newX, newY)
                && !player.getCurrentRoom().isCollidingWithWalls(newX, newY)) {
            player.setX(newX);
            player.setY(newY);
        }
    }
}
