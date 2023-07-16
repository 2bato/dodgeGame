package model;

import java.util.ArrayList;
import java.util.List;


public class HighScores {
    private List<ScoreCard> highscores;

    public HighScores() {
        highscores = new ArrayList<>();
    }

    public void addScore(int score, String time) {
        ScoreCard scoreCard = new ScoreCard(score, time);
        highscores.add(scoreCard);
    }

    public List<ScoreCard> getHighScores() {
        return highscores;
    }


}
