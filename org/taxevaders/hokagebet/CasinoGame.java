package org.taxevaders.hokagebet;

public abstract class CasinoGame {
    public User user;
    public double bet;

    public CasinoGame(User user) {
        this.user = user;
    }

    public void setBet(double bet) {
        if (bet > user.getBalance()) {
            throw new IllegalArgumentException("Bet amount cannot exceed user balance.");
        }
        this.bet = bet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBet() {
        return bet;
    }

    public abstract void playGame();
}

