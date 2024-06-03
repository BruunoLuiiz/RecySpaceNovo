package trashgame.Modelo;

public class Score {
    private int score;

    public Score() {
        this.score = 0;
    }

    public void incrementScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return this.score;
    }
}
