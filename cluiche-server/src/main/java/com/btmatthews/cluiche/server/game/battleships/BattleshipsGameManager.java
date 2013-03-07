package com.btmatthews.cluiche.server.game.battleships;

import com.threerings.parlor.game.server.GameManager;
import com.threerings.parlor.turn.server.TurnGameManager;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class BattleshipsGameManager extends GameManager implements TurnGameManager {

    private Board boards[] = new Board[]{
            new Board(),
            new Board()
    };

    public void turnWillStart() {
    }

    public void turnDidStart() {
    }

    public void turnDidEnd() {
    }
}
