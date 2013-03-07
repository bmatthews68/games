package com.btmatthews.cluiche.server.service;

import java.util.List;

import com.btmatthews.cluiche.server.game.Game;
import com.btmatthews.cluiche.server.player.Player;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public interface GameService {

    List<? extends Game> listGames();

    Game newGame(String name, int minPlayers, int maxPlayers);

    Game findGameById(String identifier);

    Game updateGame(String identifier, String name, int minPlayers, int maxPlayers);

    void deleteGame(String identifier);
}
