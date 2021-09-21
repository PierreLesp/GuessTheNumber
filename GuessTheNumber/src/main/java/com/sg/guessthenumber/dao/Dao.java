/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import java.util.List;

/**
 *
 * @author pierre
 */
public interface Dao {

    public List<Round> getAllRoundsByGameId(int id);
    
    public Round addNewRoundToGameId(Round round);

    public Game addNewGame(Game game);

    public boolean editIsFinished(int id);

    public List<Game> getAllGames();


}
