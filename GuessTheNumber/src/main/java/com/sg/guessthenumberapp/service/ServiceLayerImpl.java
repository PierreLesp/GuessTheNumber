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
import java.util.Arrays;
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
        Game game = dao.getGame(gameId);
        Round round = new Round();
        round.setGameId(gameId);
        round.setGuess1(guess1);
        round.setGuess2(guess2);
        round.setGuess3(guess3);
        round.setGuess4(guess4);
        dao.addNewRoundToGameId(round);
        int exactGuess = 0;
        int partialGuess = 0;
        Integer[] answer = new Integer[]{game.getAns1(), game.getAns2(), game.getAns3(), game.getAns4()};
        Integer[] guess = new Integer[]{guess1, guess2, guess3, guess4};
        List<Integer> answerList = new ArrayList<>(Arrays.asList(answer));
        for (int i = 0; i < answer.length; i++){
            if (answer[i] == guess[i]){
                exactGuess++;
            }
            else if (answerList.contains(guess[i])){
                partialGuess++;
            }
        }
        if (exactGuess == 4)
            game.setIsCompleted(true);
        dao.editIsFinished(game);
        return "e:" + exactGuess + ":p:" + partialGuess;
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
