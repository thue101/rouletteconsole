package com.thulani.rouletegame.model;

import java.math.BigDecimal;

public class Bet<E> {
    protected BigDecimal betAmount;

    public Bet(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public Bet() {
    }

    public BigDecimal getBetAmount() {
        return this.betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public String toString() {
        return "Bet(betAmount=" + this.getBetAmount() + ")";
    }
}
