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
        final String INSERT_ROUND = "INSERT INTO round(gameId, roundId, guess1, guess2, guess3, guess4) "
                + "VALUES(?,?,?,?,?,?)";
        jdbc.update(INSERT_ROUND,
                round.getGameId(),
                round.getRoundId(),
                round.getGuess1(),
                round.getGuess2(),
                round.getGuess3(),
                round.getGuess4());
        return round;
    }

    @Override
    @Transactional
    public Game addNewGame(Game game){
        final String INSERT_GAME = "INSERT INTO game(ans1, ans2, ans3, ans4, isCompleted) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_GAME,
                game.getAns1(),
                game.getAns2(),
                game.getAns3(),
                game.getAns4(),
                game.isIsCompleted());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setId(newId);
        return game;
    }

    @Override
    public boolean editIsFinished(Game game){

        final String sql = "UPDATE game SET "
                + "id = ?, "
                + "ans1 = ?, "
                + "ans2 = ?, "
                + "ans3 = ?, "
                + "ans4 = ?, "
                + "isCompleted = ?"
                + "WHERE id = ?;";

        return jdbc.update(sql,
                game.getId(),
                game.getAns1(),
                game.getAns2(),
                game.getAns3(),
                game.getAns4(),
                game.isIsCompleted()) > 0;
    }

    @Override
    public List<Game> getAllGames(){
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setAns1(rs.getInt("ans1"));
            game.setAns2(rs.getInt("ans2"));
            game.setAns3(rs.getInt("ans3"));
            game.setAns4(rs.getInt("ans4"));
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
            round.setGuess1(rs.getInt("guess1"));
            round.setGuess2(rs.getInt("guess2"));
            round.setGuess3(rs.getInt("guess3"));
            round.setGuess4(rs.getInt("guess4"));
            return round;
        }
    }
}
