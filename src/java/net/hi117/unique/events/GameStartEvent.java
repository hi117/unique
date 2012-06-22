package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
* @author Yanus Poluektovich (ypoluektovich@gmail.com)
*/
public class GameStartEvent extends AbstractEvent<Game> {

	public GameStartEvent() {
		super(0, -1);
	}

	@Override
	public void trigger(final Game game) throws EventException {
		try {
			ourGameUserInterface.init(game);
			ourGameUserInterface.message("Welcome to Unique!");
			ourGameUserInterface.message(
					"In this game, " +
							"you must not allow a value to become negative " +
							"for twenty seconds."
			);
			ourGameUserInterface.message("Good luck!");
			ourGameUserInterface.message("");
		} catch (Exception e) {
			throw new EventException(this, e);
		}

		game.getTimeline().addEvent(new TurnStartEvent(0));
	}

}
