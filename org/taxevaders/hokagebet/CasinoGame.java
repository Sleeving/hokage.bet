package org.taxevaders.hokagebet;

public abstract class CasinoGame {
    protected User user;
    protected double bet;
    private String gameName;
    private double minBet;
    private double maxBet;

    public CasinoGame(User user, String gameName, double minBet, double maxBet) {
        this.user = user;
        this.gameName = gameName;
        this.minBet = minBet;
        this.maxBet = maxBet;
    }

    public CasinoGame(User user) {
    }

    public void setBet(double bet) {
        if (bet > user.getBalance() || bet < minBet || bet > maxBet) {
            throw new IllegalArgumentException("Bet cannot exceed user's balance or be outside the game's betting range.");
        }
        this.bet = bet;
    }

    public double getBet() {
        return bet;
    }

    public String getGameName() {
        return gameName;
    }

    public double getMinBet() {
        return minBet;
    }

    public double getMaxBet() {
        return maxBet;
    }

    public abstract void playGame();
}

