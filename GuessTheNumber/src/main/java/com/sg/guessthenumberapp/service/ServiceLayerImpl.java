/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumberapp.service;

import com.sg.guessthenumberapp.dao.Dao;
import com.sg.guessthenumberapp.dto.Game;
import com.sg.guessthenumberapp.dto.GameDisplay;
import com.sg.guessthenumberapp.dto.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pierre
 */
@Component
public class ServiceLayerImpl implements ServiceLayer {

    @Autowired
    Dao dao;

    @Override
    public List<Round> getAllRoundsByGameId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String guess(int gameId, int guess1, int guess2, int guess3, int guess4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameDisplay addNewGame() {
        Random rnd = new Random();
        List<Integer> answer = new ArrayList<Integer>();
        while (answer.size() < 4){
            int randomNumber = rnd.nextInt(10);
            if (!answer.contains(randomNumber))
                answer.add(randomNumber);
        }
        Game game = new Game();
        game.setAns1(answer.get(0));
        game.setAns2(answer.get(1));
        game.setAns3(answer.get(2));
        game.setAns4(answer.get(3));
        dao.addNewGame(game);
        GameDisplay returnValue = new GameDisplay();
        
        returnValue.setId(game.getId());
        returnValue.setIsCompleted(game.isIsCompleted());
        
        return returnValue;
    }

    @Override
    public GameDisplay getGame(int id) 
    {
        
        Game game = dao.getGame(id);
        
        GameDisplay returnValue = new GameDisplay();
        
        returnValue.setId(game.getId());
        returnValue.setIsCompleted(game.isIsCompleted());
        
        return returnValue;
    }

    @Override
    public List<GameDisplay> getAllGames() 
    {
        List<Game> games = dao.getAllGames();
        
        List<GameDisplay> gamesDisplay = new ArrayList<GameDisplay>();
        
        for(Game game : games)
        {
            GameDisplay returnValue = new GameDisplay();
        
            returnValue.setId(game.getId());
            returnValue.setIsCompleted(game.isIsCompleted());
            
            gamesDisplay.add(returnValue);
        }
        
        return gamesDisplay;
        
    }

    @Override
    public void deleteGameById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
