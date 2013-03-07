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

package com.btmatthews.cluiche.server.controller;

import java.util.List;
import java.util.regex.Pattern;

import com.btmatthews.cluiche.server.player.Player;
import com.btmatthews.cluiche.server.service.PlayerService;
import com.btmatthews.cluiche.server.webapp.PlayerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@Controller
public class PlayerAPIController {

    private static final String UUID_REGEX = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    private static final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX);

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    @ResponseBody
    public List<? extends Player> listPlayers() {
        return playerService.listPlayers();
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Player newPlayer(final PlayerForm form) {
        return playerService.newPlayer(form.getUsername(), form.getFullName(), form.getPassword());
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Player getPlayer(@PathVariable("id") final String id) {
        if (UUID_PATTERN.matcher(id).matches()) {
            return playerService.findPlayerById(id);
        } else {
            return playerService.findPlayerByUsername(id);
        }
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Player updatePlayer(@PathVariable("id") final String id, final PlayerForm form) {
        return playerService.updatePlayer(id, form.getUsername(), form.getFullName(), form.getPassword());
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable("id") final String identifier) {
        playerService.deletePlayer(identifier);
    }
}



