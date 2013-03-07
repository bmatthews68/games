package com.btmatthews.cluiche.server.controller;

import java.util.List;

import com.btmatthews.cluiche.server.game.Game;
import com.btmatthews.cluiche.server.service.GameService;
import com.btmatthews.cluiche.server.webapp.GameForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 25/08/12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GameAPIController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseBody
    public List<? extends Game> listGames() {
        return gameService.listGames();
    }

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Game newGame(final GameForm form) {
        return gameService.newGame(form.getName(), form.getMinPlayers(), form.getMaxPlayers());
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Game getGame(@PathVariable("id") final String id) {
        return gameService.findGameById(id);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Game updateGame(@PathVariable("id") final String id, final GameForm form) {
        return gameService.updateGame(id, form.getName(), form.getMinPlayers(), form.getMaxPlayers());
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable("id") final String identifier) {
        gameService.deleteGame(identifier);
    }
}
