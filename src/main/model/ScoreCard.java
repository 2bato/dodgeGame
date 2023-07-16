package model;

/*
 * Represents a single score entry with score and time
 */
public class ScoreCard {

    private int score;
    private String time;


    public ScoreCard(int score, String time) {
        this.time = time;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    public String getScoreCardString() {
        return score + " " + time;
    }
}
