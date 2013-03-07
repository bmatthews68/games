package com.btmatthews.cluiche.server.webapp;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 25/08/12
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class GameForm {

    private String name;

    private int minPlayers;

    private int maxPlayers;

    public String getName() {
        return name;
    }

    public void setName(final String value) {
        name = value;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(final int value) {
        minPlayers = value;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(final int value) {
        maxPlayers = value;
    }
}
