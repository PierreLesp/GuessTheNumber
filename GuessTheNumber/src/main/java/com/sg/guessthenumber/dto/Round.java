/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dto;

import java.util.Objects;

/**
 *
 * @author pierre
 */
public class Round {

    private int roundId;

    private int gameId;

    private int guess1;

    private int guess2;

    private int guess3;

    private int guess4;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGuess1() {
        return guess1;
    }

    public void setGuess1(int guess1) {
        this.guess1 = guess1;
    }

    public int getGuess2() {
        return guess2;
    }

    public void setGuess2(int guess2) {
        this.guess2 = guess2;
    }

    public int getGuess3() {
        return guess3;
    }

    public void setGuess3(int guess3) {
        this.guess3 = guess3;
    }

    public int getGuess4() {
        return guess4;
    }

    public void setGuess4(int guess4) {
        this.guess4 = guess4;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.roundId;
        hash = 59 * hash + this.gameId;
        hash = 59 * hash + this.guess1;
        hash = 59 * hash + this.guess2;
        hash = 59 * hash + this.guess3;
        hash = 59 * hash + this.guess4;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.guess1 != other.guess1) {
            return false;
        }
        if (this.guess2 != other.guess2) {
            return false;
        }
        if (this.guess3 != other.guess3) {
            return false;
        }
        if (this.guess4 != other.guess4) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", gameId=" + gameId + ", guess1=" + guess1 + ", guess2=" + guess2 + ", guess3=" + guess3 + ", guess4=" + guess4 + '}';
    }

}
