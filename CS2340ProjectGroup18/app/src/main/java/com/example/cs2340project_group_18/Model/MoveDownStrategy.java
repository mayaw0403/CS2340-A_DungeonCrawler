package com.example.cs2340project_group_18.Model;

public class MoveDownStrategy implements MovementStrategy {
    @Override
    public void move(Player player) {
        int newX = player.getX();
        int newY = player.getY() + 1;


        if (player.isWithinScreenBoundaries(newX, newY)
                && !player.getCurrentRoom().isCollidingWithWalls(newX, newY)) {
            player.setX(newX);
            player.setY(newY);
        }
    }
}
