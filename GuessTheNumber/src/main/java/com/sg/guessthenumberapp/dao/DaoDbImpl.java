/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumberapp.dao;

import com.sg.guessthenumberapp.dto.Game;
import com.sg.guessthenumberapp.dto.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
        final String sql = "INSERT INTO round(gameId, guess1, guess2, guess3, guess4) "
                + "VALUES(?,?,?,?,?)";



         GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGameId());
            statement.setInt(2, round.getGuess1());
            statement.setInt(3, round.getGuess2());
            statement.setInt(4, round.getGuess3());
            statement.setInt(5, round.getGuess4());
            return statement;

        }, keyHolder);

        round.setRoundId(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    @Transactional
    public Game addNewGame(Game game){
        final String sql = "INSERT INTO game(ans1, ans2, ans3, ans4, isCompleted) "
                + "VALUES(?,?,?,?,?)";


        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, game.getAns1());
            statement.setInt(2, game.getAns2());
            statement.setInt(3, game.getAns3());
            statement.setInt(4, game.getAns4());
            statement.setBoolean(5, game.isIsCompleted());
            return statement;

        }, keyHolder);

        game.setId(keyHolder.getKey().intValue());




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
                + "isCompleted = ? "
                + "WHERE id = ?;";

        return jdbc.update(sql,
                game.getId(),
                game.getAns1(),
                game.getAns2(),
                game.getAns3(),
                game.getAns4(),
                game.isIsCompleted(),
                game.getId()) > 0;
    }

    @Override
    public Game getGame(int id){
        final String SELECT_GAME = "SELECT * FROM game WHERE id = ?";
        jdbc.update(SELECT_GAME, id);
        List<Game> games = jdbc.query(SELECT_GAME, new GameMapper());
        if (games.isEmpty())
            return null;
        else
            return games.get(0);
    }

    @Override
    public List<Game> getAllGames(){
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    @Transactional
    public void deleteGameById(int id) {
        final String DELETE_ROUND = "DELETE FROM round "
                + "WHERE gameId = ?";
        jdbc.update(DELETE_ROUND, id);

        final String DELETE_GAME = "DELETE FROM game WHERE id = ?";
        jdbc.update(DELETE_GAME, id);
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
