package model;

import java.util.*;


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

    public List<String> getTopHighScores() {
        List<String> topHighScores = new ArrayList<>();
        highscores.sort(Comparator.comparingInt(ScoreCard::getScore).reversed());
        for (ScoreCard scoreCard : highscores) {
            topHighScores.add(scoreCard.getScoreCardString());
        }
        return topHighScores;
    }
}
