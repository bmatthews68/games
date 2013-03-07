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

package com.btmatthews.cluiche.server.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import com.btmatthews.atlas.core.id.IdentifierGenerator;
import com.btmatthews.cluiche.server.player.Player;
import com.btmatthews.cluiche.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    /**
     * Generates UUID based identifiers for {@link Player} entities.
     */
    private IdentifierGenerator identifierGenerator;

    /**
     * The {@link MongoTemplate} instance that is used to access the MongoDB database.
     */
    private MongoTemplate mongoTemplate;

    /**
     * Used to inject the {@link IdentifierGenerator} instance that generates UUID based identifiers for {@link Player}
     * entities.
     *
     * @param generator The UUID based identifier generator.
     */
    @Autowired
    public void setIdentifierGenerator(final IdentifierGenerator generator) {
        identifierGenerator = generator;
    }

    /**
     * Used to inject the {@link MongoTemplate} instance that is used to access the MongoDB database.
     *
     * @param template The {@link MongoTemplate} instance.
     */
    @Autowired
    public void setMongoTemplate(final MongoTemplate template) {
        mongoTemplate = template;
    }

    @Override
    public List<? extends Player> listPlayers() {
        return mongoTemplate.findAll(PlayerImpl.class);
    }

    @Override
    public Player newPlayer(final String username, final String fullName, final String password) {
        final String identifier = identifierGenerator.generateIdentifier();
        final Player player = new PlayerImpl(identifier, username, fullName, password);
        mongoTemplate.insert(player);
        return player;
    }

    @Override
    public Player findPlayerById(final String identifier) {
        return mongoTemplate.findById(identifier, PlayerImpl.class);
    }

    @Override
    public Player findPlayerByUsername(final String username) {
        return mongoTemplate.findOne(query(where("username").is(username)), PlayerImpl.class);
    }

    @Override
    public Player updatePlayer(final String identifier, final String username, final String fullName,
                             final String password) {
        final Player player = new PlayerImpl(identifier, username, fullName, password);
        mongoTemplate.save(player);
        return player;
    }

    @Override
    public void deletePlayer(final String identifier) {
        mongoTemplate.remove(query(where("_id").is(identifier)), PlayerImpl.class);
    }
}
