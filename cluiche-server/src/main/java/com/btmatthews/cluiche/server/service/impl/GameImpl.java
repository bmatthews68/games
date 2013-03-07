package com.btmatthews.cluiche.server.service.impl;

import java.util.List;

import com.btmatthews.cluiche.server.game.BoardDescription;
import com.btmatthews.cluiche.server.game.Game;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 25/08/12
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class GameImpl implements Game {

    private String id;

    private String name;

    private int minPlayers;

    private int maxPlayers;

    private List<BoardDescription> boards;

    public GameImpl(final String id, final String name, int minPlayers, int maxPlayers) {
        this.id = id;
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public List<BoardDescription> getBoards() {
        return boards;
    }
}
