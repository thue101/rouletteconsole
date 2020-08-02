package com.thulani.rouletegame.model;

import com.thulani.rouletegame.value.BetValue;

import java.math.BigDecimal;

public class NumberBet<Integer> extends Bet<Integer> {

    private BetValue<Integer> betValue;

    public NumberBet(BetValue<Integer> betValue, BigDecimal betAmount) {
        super(betAmount);
        this.betValue = betValue;


    }

    public NumberBet() {
    }

    public BetValue<Integer> getBetValue() {
        return betValue;
    }

    public void setBetValue(BetValue<Integer> betValue) {
        this.betValue = betValue;
    }

    @Override
    public String toString() {
        return "NumberBet{" +
                "betValue=" + betValue +
                ", betAmount=" + betAmount +
                '}';
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NumberBet)) return false;
        final NumberBet<?> other = (NumberBet<?>) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$betValue = this.getBetValue();
        final Object other$betValue = other.getBetValue();
        if (this$betValue == null ? other$betValue != null : !this$betValue.equals(other$betValue)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NumberBet;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $betValue = this.getBetValue();
        result = result * PRIME + ($betValue == null ? 43 : $betValue.hashCode());
        return result;
    }
}
