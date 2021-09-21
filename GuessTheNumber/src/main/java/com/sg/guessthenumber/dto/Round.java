/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dto;

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
    
    
    
    
    
}
