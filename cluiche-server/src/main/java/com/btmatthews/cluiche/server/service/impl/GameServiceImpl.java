package com.btmatthews.cluiche.server.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import com.btmatthews.atlas.core.id.IdentifierGenerator;
import com.btmatthews.cluiche.server.game.Game;
import com.btmatthews.cluiche.server.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @author 1.0.0
 */
@Service
public class GameServiceImpl implements GameService {

    /**
     * Generates UUID based identifiers for {@link Game} entities.
     */
    private IdentifierGenerator identifierGenerator;

    /**
     * The {@link org.springframework.data.mongodb.core.MongoTemplate} instance that is used to access the MongoDB database.
     */
    private MongoTemplate mongoTemplate;

    /**
     * Used to inject the {@link IdentifierGenerator} instance that generates UUID based identifiers for {@link Game}
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

    public List<? extends Game> listGames() {
        return mongoTemplate.findAll(GameImpl.class);
    }

    public Game newGame(final String name, final int minPlayers, final int maxPlayers) {
        final String identifier = identifierGenerator.generateIdentifier();
        final Game game = new GameImpl(identifier, name, minPlayers, maxPlayers);
        mongoTemplate.insert(game);
        return game;
    }

    public Game findGameById(final String identifier) {
        return mongoTemplate.findById(identifier, GameImpl.class);
    }

    public Game updateGame(final String identifier, final String name, final int minPlayers, final int maxPlayers) {
        final Game game = new GameImpl(identifier, name, minPlayers, maxPlayers);
        mongoTemplate.save(game);
        return game;
    }

    public void deleteGame(final String identifier) {
         mongoTemplate.remove(query(where("_id").is(identifier)));
    }
}
