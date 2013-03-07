/*
    Copyright (c) 2012 Brian Matthews

    This file is part of the Cluiche Server.

    Cluiche Server is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Cluiche Server is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Cluiche Server.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.btmatthews.cluiche.server.service;

import java.util.List;

import com.btmatthews.cluiche.server.player.Player;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public interface PlayerService {

    List<? extends Player> listPlayers();

    Player newPlayer(String username, String fullName, String password);

    Player findPlayerById(String identifier);

    Player findPlayerByUsername(String username);

    Player updatePlayer(String identifier, String username, String fullName, String password);

    void deletePlayer(String identifier);
}
