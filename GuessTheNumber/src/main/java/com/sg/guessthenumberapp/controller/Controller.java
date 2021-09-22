    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumberapp.controller;

import com.sg.guessthenumberapp.dao.Dao;
import com.sg.guessthenumberapp.dto.Game;
import com.sg.guessthenumberapp.dto.GameDisplay;
import com.sg.guessthenumberapp.dto.Round;
import com.sg.guessthenumberapp.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pierre
 */


@RequestMapping("/")
@RestController
public class Controller
{

    private final ServiceLayer service;
    private final Dao dao;

    @Autowired
    public Controller(Dao dao, ServiceLayer service)
    {
        this.dao = dao;
        this.service = service;
    }


    @RequestMapping("/begin/")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDisplay createGame()
    {
        return service.addNewGame();
    }


    @RequestMapping("/guess/")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Round createRound(@RequestBody Round round)
    {
        return dao.addNewRoundToGameId(round);
    }

    @RequestMapping("/game/")
    @GetMapping
    public List<GameDisplay> getAllGames()
    {
        return service.getAllGames();
    }




    @GetMapping("/rounds/{id}/")
    public ResponseEntity<List<Round>> findById(@PathVariable int id)
    {
        List<Round> result = dao.getAllRoundsByGameId(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }


    // TO DO
        //IMPLEMENT


    @RequestMapping("/game/{id}")
    @GetMapping
    public GameDisplay functionName(@PathVariable int id)
    {
        return service.getGame(id);
    }



}
