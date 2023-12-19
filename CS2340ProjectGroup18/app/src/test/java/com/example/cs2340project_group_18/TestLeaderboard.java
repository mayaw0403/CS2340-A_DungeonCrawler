package com.example.cs2340project_group_18;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import com.example.cs2340project_group_18.Model.Attempt;
import com.example.cs2340project_group_18.View.Leaderboard;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestLeaderboard {

    @Test
    public void testLeaderboardUpdatesDescendingOrder() {
        Leaderboard leaderboard = Leaderboard.getInstance();

        Attempt attempt1 = new Attempt("Player1", 500, new Date());
        Attempt attempt2 = new Attempt("Player2", 700, new Date());
        Attempt attempt3 = new Attempt("Player3", 300, new Date());

        leaderboard.addAttempt(attempt1);
        leaderboard.addAttempt(attempt2);
        leaderboard.addAttempt(attempt3);

        List<Attempt> topAttempts = leaderboard.getTopAttempts(3);

        assertEquals(3, topAttempts.size());
        assertTrue(topAttempts.get(0).getScore() >= topAttempts.get(1).getScore());
        assertTrue(topAttempts.get(1).getScore() >= topAttempts.get(2).getScore());
    }

    @Test
    public void testLeaderboardDisplaysMostRecentAttempt() {
        Leaderboard leaderboard = Leaderboard.getInstance();

        Attempt attempt1 = new Attempt("Player1", 500, new Date());
        Attempt attempt2 = new Attempt("Player2", 700, new Date());
        Attempt attempt3 = new Attempt("Player3", 300, new Date());

            leaderboard.addAttempt(attempt1);
            leaderboard.addAttempt(attempt2);
            leaderboard.addAttempt(attempt3);

            Attempt recentAttempt = new Attempt("Player4", 800, new Date());
            leaderboard.addAttempt(recentAttempt);


            List<Attempt> topScores = leaderboard.getTopAttempts(5);

            // Ensure the most recent attempt is displayed on the leaderboard
            assertTrue(topScores.contains(recentAttempt));
        }
}



