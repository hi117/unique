package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
* @author Yanus Poluektovich (ypoluektovich@gmail.com)
*/
public class GameStartEvent extends AbstractEvent {

	public GameStartEvent(final Game game) {
		super(0, -1, game);
	}

	@Override
	public void trigger() throws EventException {
		try {
			ourGameUserInterface.init(myGame);
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

		myGame.getTimeline().addEvent(new TurnStartEvent(0, myGame));
	}

}
