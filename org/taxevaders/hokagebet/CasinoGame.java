package org.taxevaders.hokagebet;

public abstract class CasinoGame extends User {
    private String gameName;
    private double minBet;
    private double maxBet;

    public CasinoGame() {
        gameName = null;
        minBet = 0;
        maxBet = 0;
    }
    public CasinoGame(String gameName, double minBet, double maxBet) {
        this.gameName = gameName;
        this.minBet = minBet;
        this.maxBet = maxBet;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getMinBet() {
        return minBet;
    }

    public void setMinBet(double minBet) {
        this.minBet = minBet;
    }

    public double getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(double maxBet) {
        this.maxBet = maxBet;
    }
    public abstract void playGame();
}
