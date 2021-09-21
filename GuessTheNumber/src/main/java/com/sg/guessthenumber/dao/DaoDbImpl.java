/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pierre
 */
@Repository
public class DaoDbImpl implements Dao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Round> getAllRoundsByGameId(int id) {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM round WHERE gameId = ?";
        return jdbc.query(SELECT_ALL_ROUNDS, new RoundMapper(), id);
    }

    @Override
    @Transactional
    public Round addNewRoundToGameId(Round round) {
        final String INSERT_ROUND = "INSERT INTO round(gameId, roundId, num1, num2, num3, num4) "
                + "VALUES(?,?,?,?,?,?)";
        int[] guess = round.getGuess();
        jdbc.update(INSERT_ROUND,
                round.getGameId(),
                round.getRoundId(),
                guess[0],
                guess[1],
                guess[2],
                guess[3]);
        return round;
    }

    public Game addNewGame(Game game){
        final String INSERT_GAME = "INSERT INTO game(ans1, ans2, ans3, ans4, isCompleted) "
                + "VALUES(?,?,?,?,?)";
        int[] answer = game.getAnswer();
        jdbc.update(INSERT_GAME,
                answer[0],
                answer[1],
                answer[2],
                answer[3],
                game.isIsCompleted());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setId(newId);
        return game;
    }

    public boolean editIsFinished(int id){

    }

    public List<Game> getAllGames(){
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            int[] answer = {rs.getInt("ans1"), rs.getInt("ans2"), rs.getInt("ans3"), rs.getInt("ans4")};
            game.setAnswer(answer);
            game.setIsCompleted(rs.getBoolean("isCompleted"));
            return game;
        }
    }



    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setGameId(rs.getInt("gameId"));
            round.setRoundId(rs.getInt("roundId"));
            int[] guess = {rs.getInt("num1"), rs.getInt("num2"), rs.getInt("num3"), rs.getInt("num4")};
            round.setGuess(guess);
            return round;
        }
    }
}
