/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumberapp.dao;

import com.sg.guessthenumberapp.TestApplicationConfiguration;
import com.sg.guessthenumberapp.dto.Game;
import com.sg.guessthenumberapp.dto.Round;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author kavin
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DaoDbImplTest {

    @Autowired
    Dao dao;

    public DaoDbImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Game> games = dao.getAllGames();
        for (Game game : games) {
            dao.deleteGameById(game.getId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllRoundsByGameId method, of class DaoDbImpl.
     */
    @Test
    public void testAddGetAllRoundsByGameId() {
        Game game = new Game();
        game.setAns1(1);
        game.setAns2(2);
        game.setAns3(3);
        game.setAns4(4);
        Game addedGame = dao.addNewGame(game);
        assertNotNull(addedGame);

        Round round1 = new Round();
        round1.setGameId(addedGame.getId());
        round1.setGuess1(2);
        round1.setGuess2(3);
        round1.setGuess3(4);
        round1.setGuess4(5);

        Round addedRound1 = dao.addNewRoundToGameId(round1);
        assertNotNull(addedRound1);
        List<Round> gameRounds = dao.getAllRoundsByGameId(addedGame.getId());
        assertNotNull(gameRounds);
        assertEquals(1, gameRounds.size());
        assertEquals(addedRound1, gameRounds.get(0));

        Round round2 = new Round();
        round2.setGameId(addedGame.getId());
        round2.setGuess1(2);
        round2.setGuess2(3);
        round2.setGuess3(4);
        round2.setGuess4(6);

        Round addedRound2 = dao.addNewRoundToGameId(round2);
        assertNotNull(addedRound2);
        gameRounds = dao.getAllRoundsByGameId(addedGame.getId());
        assertNotNull(gameRounds);
        assertEquals(2, gameRounds.size());
        assertEquals(addedRound1, gameRounds.get(0));
        assertEquals(addedRound2, gameRounds.get(1));
    }

    /**
     * Test of addNewGame method, of class DaoDbImpl.
     */
    @Test
    public void testAddGetAllGame() {
        Game game = new Game();
        game.setAns1(1);
        game.setAns2(2);
        game.setAns3(3);
        game.setAns4(4);
        Game addedGame = dao.addNewGame(game);
        assertNotNull(addedGame);
        List<Game> gameList = dao.getAllGames();
        assertNotNull(gameList);
        assertEquals(1, gameList.size());
        assertEquals(gameList.get(0), addedGame);

        Game game2 = new Game();
        game2.setAns1(1);
        game2.setAns2(2);
        game2.setAns3(3);
        game2.setAns4(5);

        Game addedGame2 = dao.addNewGame(game2);
        assertNotNull(addedGame2);
        gameList = dao.getAllGames();
        assertNotNull(gameList);
        assertEquals(2, gameList.size());
        assertEquals(gameList.get(0), addedGame);
        assertEquals(gameList.get(1), addedGame2);
    }

    /**
     * Test of editIsFinished method, of class DaoDbImpl.
     */
    @Test
    public void testEditIsFinished() {
        Game game = new Game();
        game.setAns1(1);
        game.setAns2(2);
        game.setAns3(3);
        game.setAns4(4);
        Game addedGame = dao.addNewGame(game);
        assertNotNull(addedGame);
        addedGame.setIsCompleted(true);
        assertTrue(dao.editIsFinished(game));
        addedGame.setId(addedGame.getId() + 1);
        assertFalse(dao.editIsFinished(game));
    }


}