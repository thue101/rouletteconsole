package com.thulani.rouletegame.model;

import java.math.BigDecimal;
import java.util.Set;

public class Player {
    private String name;

    private Set<Bet<?>> bets;

    private int numberOfWins;

    private BigDecimal totalAmountWonSoFar;

    Player(String name, Set<Bet<?>> bets, int numberOfWins, BigDecimal totalAmountWonSoFar) {
        this.name = name;
        this.bets = bets;
        this.numberOfWins = numberOfWins;
        this.totalAmountWonSoFar = totalAmountWonSoFar;
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Bet<?>> getBets() {
        return this.bets;
    }

    public void setBets(Set<Bet<?>> bets) {
        this.bets = bets;
    }

    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public BigDecimal getTotalAmountWonSoFar() {
        return this.totalAmountWonSoFar;
    }

    public void setTotalAmountWonSoFar(BigDecimal totalAmountWonSoFar) {
        this.totalAmountWonSoFar = totalAmountWonSoFar;
    }

    public String toString() {
        return "Player(name=" + this.getName() + ", bets=" + this.getBets() + ", numberOfWins=" + this.getNumberOfWins() + ", totalAmountWonSoFar=" + this.getTotalAmountWonSoFar() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Player)) return false;
        final Player other = (Player) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Player;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public static class PlayerBuilder {
        private String name;
        private Set<Bet<?>> bets;
        private int numberOfWins;
        private BigDecimal totalAmountWonSoFar;

        PlayerBuilder() {
        }

        public Player.PlayerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Player.PlayerBuilder bets(Set<Bet<?>> bets) {
            this.bets = bets;
            return this;
        }

        public Player.PlayerBuilder numberOfWins(int numberOfWins) {
            this.numberOfWins = numberOfWins;
            return this;
        }

        public Player.PlayerBuilder totalAmountWonSoFar(BigDecimal totalAmountWonSoFar) {
            this.totalAmountWonSoFar = totalAmountWonSoFar;
            return this;
        }

        public Player build() {
            return new Player(name, bets, numberOfWins, totalAmountWonSoFar);
        }

        public String toString() {
            return "Player.PlayerBuilder(name=" + this.name + ", bets=" + this.bets + ", numberOfWins=" + this.numberOfWins + ", totalAmountWonSoFar=" + this.totalAmountWonSoFar + ")";
        }
    }
}
