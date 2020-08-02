package com.thulani.rouletegame.model;

import com.thulani.rouletegame.value.BetValue;

import java.math.BigDecimal;

public class EvenOddBet<EvenOddBetEnum> extends Bet<EvenOddBetEnum> {
    private BetValue<EvenOddBetEnum> betValue;

    public EvenOddBet(BetValue<EvenOddBetEnum> betValue, BigDecimal betAmount) {
        super(betAmount);
        this.betValue = betValue;
    }

    @Override
    public String toString() {
        return "EvenOddBet{" +
                "betValue=" + betValue +
                ", betAmount=" + betAmount +
                '}';
    }
}
