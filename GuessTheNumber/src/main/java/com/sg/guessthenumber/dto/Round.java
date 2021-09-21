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
    
    int[] guess;

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

    public int[] getGuess() {
        return guess;
    }

    public void setGuess(int[] guess) {
        this.guess = guess;
    }
    
    
    
    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.gameId);
        hash = 89 * hash + Objects.hashCode(this.roundId);
        hash = 89 * hash + Objects.hashCode(this.guess);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
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
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.roundId, other.roundId)) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        return true;
    }    
    
    @Override
    public String toString() 
    {
        return "Round{" + "GameId=" + gameId + ", roundId=" + roundId + ", guess=" + guess + '}';
    }
    
    
}
