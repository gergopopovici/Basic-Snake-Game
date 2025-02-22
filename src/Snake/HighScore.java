package Snake;

import java.io.Serializable;

public class HighScore implements Comparable<HighScore>, Serializable {
    private String playerName;
    private int score;

    public HighScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(HighScore other) {
        // Compare scores in descending order
        return Integer.compare(other.getScore(), this.getScore());
    }
}
