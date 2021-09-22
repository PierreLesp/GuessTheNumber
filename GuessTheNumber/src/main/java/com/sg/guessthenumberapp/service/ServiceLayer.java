/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumberapp.service;

import com.sg.guessthenumberapp.dto.Game;
import com.sg.guessthenumberapp.dto.GameDisplay;
import com.sg.guessthenumberapp.dto.Round;
import java.util.List;

/**
 *
 * @author pierre
 */
public interface ServiceLayer {

    public List<Round> getAllRoundsByGameId(int id);

    public String guess(int gameId, int guess1, int guess2, int guess3, int guess4);

    public GameDisplay addNewGame();

    public GameDisplay getGame(int id);

    public List<GameDisplay> getAllGames();

    public void deleteGameById(int id);
}
